package ru.bellintegrator.db_service.service;

import com.caucho.hessian.server.HessianServlet;
//import org.springframework.transaction.annotation.Transactional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.db_service.dao.ResultDao;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.db_service.model.ForecastEntity;
import ru.bellintegrator.db_service.model.LocationEntity;
import ru.bellintegrator.db_service.service.astronomy.AstronomyService;
import ru.bellintegrator.db_service.service.atmosphere.AtmosphereService;
import ru.bellintegrator.db_service.service.condition.ConditionService;
import ru.bellintegrator.db_service.service.currentobservation.CurrentObservationService;
import ru.bellintegrator.db_service.service.forecast.ForecastService;
import ru.bellintegrator.db_service.service.location.LocationService;
import ru.bellintegrator.db_service.service.wind.WindService;
import ru.bellintegrator.exception.CustomException;
import ru.bellintegrator.weatherparser.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DBServiceImpl extends HessianServlet implements DBService {

    private final Logger LOG = LoggerFactory.getLogger(DBServiceImpl.class);

    @Inject
    private ResultDao dao;

    @Inject
    private AstronomyService astronomyService;

    @Inject
    private AtmosphereService atmosphereService;

    @Inject
    private ConditionService conditionService;

    @Inject
    private WindService windService;

    @Inject
    private CurrentObservationService currentObservationService;

    @Inject
    private LocationService locationService;

    @Inject
    private ForecastService forecastService;


    @Override
    @Transactional
    public Result getResult(String city,String region) {
        Result userView = new Result();

        LocationEntity locationByCity = dao.loadLocationByCity(city,region);
        if (locationByCity == null) {
            return null;
        } else {
            Location locationView = locationService.mapEntityToView(locationByCity);
            userView.setLocation(locationView);
        }

        List<ForecastEntity> forecastEntities = dao.load10DaysForecastsByCity(locationByCity.getWoeid());
        if (forecastEntities == null) {
            return null;
        } else {
            List<Forecast> forecastViews = new ArrayList<>();
            for (ForecastEntity forecastEntity : forecastEntities) {
                forecastViews.add(forecastService.mapEntityToView(forecastEntity));
            }
            userView.setForecasts(forecastViews);
        }


        CurrentObservationEntity currentObservationEntity = dao.loadLatestCOByLocation(locationByCity.getWoeid());
        if (currentObservationEntity == null) {
            return null;
        } else {
            CurrentObservation currentObservationView = currentObservationService.mapEntityToView(currentObservationEntity);
            userView.setCurrentObservation(currentObservationView);
        }
        return userView;
    }

    @Override
    @Transactional
    public void saveResult(Result resultJson) throws CustomException {

        locationService.saveElement(resultJson.getLocation(),new LocationEntity());
        LOG.info("Location saved successfully: {}", resultJson.getLocation());

        List<Forecast> forecastJsons=resultJson.getForecasts();
        LocationEntity locationEntity = dao.loadLocationByWoeid(resultJson.getLocation().getWoeid());
        for(Forecast oneDayForecast : forecastJsons) {
            forecastService.saveElement(oneDayForecast,locationEntity);
        }

        LOG.info("Forecast saved successfully: {}", resultJson.getForecasts());

        LOG.info("TRANSACTION SUCCESSFUL, LOCATION AND FORECASTS SAVED TO DB");
    }

    @Override
    public void saveCurObserv(Result resultJson) {
        currentObservationService.saveElement(resultJson.getCurrentObservation(), dao.loadLocationByWoeid(resultJson.getLocation().getWoeid()));
        LOG.info("CurrentObservation saved successfully: {}", resultJson.getCurrentObservation());
    }

    @Override
    @Transactional
    public void saveCurObservDetails(Result resultJson) {
        astronomyService.saveElement(resultJson.getCurrentObservation().getAstronomy(),
                dao.loadCOByLocationAndDate(resultJson.getLocation().getWoeid(), resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Astronomy info saved successfully: {}", resultJson.getCurrentObservation().getAstronomy());

        atmosphereService.saveElement(resultJson.getCurrentObservation().getAtmosphere(),
                dao.loadCOByLocationAndDate(resultJson.getLocation().getWoeid(), resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Atmosphere info saved successfully: {}", resultJson.getCurrentObservation().getAtmosphere());

        conditionService.saveElement(resultJson.getCurrentObservation().getCondition(),
                dao.loadCOByLocationAndDate(resultJson.getLocation().getWoeid(), resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Condition info saved successfully: {}", resultJson.getCurrentObservation().getCondition());

        windService.saveElement(resultJson.getCurrentObservation().getWind(),
                dao.loadCOByLocationAndDate(resultJson.getLocation().getWoeid(), resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Wind info saved successfully: {}", resultJson.getCurrentObservation().getWind());

        LOG.info("TRANSACTION SUCCESSFUL, CURRENT_OBSERVATION DETAILS SAVED TO DB");
    }
}
