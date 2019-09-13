package ru.bellintegrator.db_service.service;

import com.caucho.hessian.server.HessianServlet;
//import org.springframework.transaction.annotation.Transactional;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.db_service.dao.astronomy.ResultDao;
import ru.bellintegrator.db_service.service.astronomy.AstronomyService;
import ru.bellintegrator.db_service.service.atmosphere.AtmosphereService;
import ru.bellintegrator.db_service.service.condition.ConditionService;
import ru.bellintegrator.db_service.service.currentobservation.CurrentObservationService;
import ru.bellintegrator.db_service.service.forecast.ForecastService;
import ru.bellintegrator.db_service.service.location.LocationService;
import ru.bellintegrator.db_service.service.wind.WindService;
import ru.bellintegrator.exception.CustomException;
import ru.bellintegrator.weatherparser.Result;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
        return null;
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
