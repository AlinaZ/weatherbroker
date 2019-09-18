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
import ru.bellintegrator.weatherparser.CurrentObservation;
import ru.bellintegrator.weatherparser.Forecast;
import ru.bellintegrator.weatherparser.Location;
import ru.bellintegrator.weatherparser.Result;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DBServiceImpl extends HessianServlet implements DBService {

    private final Logger LOG= LoggerFactory.getLogger(DBServiceImpl.class);

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
    public Result getResult(String city) {
        Result userView=new Result();

        LocationEntity locationByCity=dao.loadLocationByCity(city);
        Location locationView =locationService.mapEntityToView(locationByCity);
        userView.setLocation(locationView);

        List<ForecastEntity> forecastEntities=dao.load10DaysForecastsByCity(locationByCity.getWoeid());
        List<Forecast> forecastViews=new ArrayList<>();
        for (ForecastEntity forecastEntity:forecastEntities) {
            forecastViews.add(forecastService.mapForecastEntityToView(forecastEntity));
        }
        userView.setForecasts(forecastViews);

        CurrentObservationEntity currentObservationEntity=dao.loadLatestCOByLocation(locationByCity.getWoeid());
        CurrentObservation currentObservationView =currentObservationService.mapEntityToView(currentObservationEntity);
        userView.setCurrentObservation(currentObservationView);

        return userView;
    }

    @Override
    @Transactional
    public void saveResult(Result resultJson) throws CustomException {

        locationService.saveLocation(resultJson.getLocation());
        LOG.info("Location saved succesfully: {}",resultJson.getLocation());

        forecastService.saveForecasts(resultJson.getForecasts(),resultJson.getLocation());
        LOG.info("Forecast saved succesfully: {}",resultJson.getForecasts());

        LOG.info("TRANSACTION SUCCESSFUL, LOCATION AND FORECASTS SAVED TO DB");
    }

    @Override
    public void saveCurObserv(Result resultJson){
        currentObservationService.saveCurrentObservation(resultJson.getCurrentObservation().getPubDate(),resultJson.getLocation());
        LOG.info("CurrentObservation saved succesfully: {}",resultJson.getCurrentObservation());
    }

    @Override
    @Transactional
    public void saveCurObservDetails(Result resultJson) {
        astronomyService.saveAstronomy(resultJson.getCurrentObservation().getAstronomy(),
                dao.loadByLocationAndDate(resultJson.getLocation().getWoeid(),resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Astronomy info saved succesfully: {}",resultJson.getCurrentObservation().getAstronomy());

        atmosphereService.saveAtmosphere(resultJson.getCurrentObservation().getAtmosphere(),
                dao.loadByLocationAndDate(resultJson.getLocation().getWoeid(),resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Atmosphere info saved succesfully: {}",resultJson.getCurrentObservation().getAtmosphere());

        conditionService.saveCondition(resultJson.getCurrentObservation().getCondition(),
                dao.loadByLocationAndDate(resultJson.getLocation().getWoeid(),resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Condition info saved succesfully: {}",resultJson.getCurrentObservation().getCondition());

        windService.saveWind(resultJson.getCurrentObservation().getWind(),
                dao.loadByLocationAndDate(resultJson.getLocation().getWoeid(),resultJson.getCurrentObservation().getPubDate()));
        LOG.info("Wind info saved succesfully: {}",resultJson.getCurrentObservation().getWind());

        LOG.info("TRANSACTION SUCCESSFUL, CURRENT_OBSERVATION DETAILS SAVED TO DB");
    }
}
