package ru.bellintegrator.db_service.service.currentobservation;

import ru.bellintegrator.db_service.dao.ResultDao;
import ru.bellintegrator.db_service.model.*;
import ru.bellintegrator.db_service.service.astronomy.AstronomyService;
import ru.bellintegrator.db_service.service.atmosphere.AtmosphereService;
import ru.bellintegrator.db_service.service.condition.ConditionService;
import ru.bellintegrator.db_service.service.wind.WindService;
import ru.bellintegrator.weatherparser.CurrentObservation;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CurrentObservationServiceImpl implements CurrentObservationService {

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

    @Override
    public void saveElement(CurrentObservation currentObservationJson, LocationEntity locationEntity) {
        CurrentObservationEntity currentObservationEntity = new CurrentObservationEntity();
        currentObservationEntity.setPubDate(currentObservationJson.getPubDate());
                currentObservationEntity.setLocation(locationEntity);
        dao.saveCurrentObservation(currentObservationEntity);
    }

    @Override
    public CurrentObservation mapEntityToView(CurrentObservationEntity currentObservationEntity) {
        if (currentObservationEntity == null) {
            return null;
        } else {
            CurrentObservation currentObservationView = new CurrentObservation();
            currentObservationView.setPubDate(currentObservationEntity.getPubDate());
            currentObservationView.setAstronomy(astronomyService.mapEntityToView(currentObservationEntity.getAstronomy()));
            currentObservationView.setAtmosphere(atmosphereService.mapEntityToView(currentObservationEntity.getAtmosphere()));
            currentObservationView.setCondition(conditionService.mapEntityToView(currentObservationEntity.getCondition()));
            currentObservationView.setWind(windService.mapEntityToView(currentObservationEntity.getWind()));
            return currentObservationView;
        }
    }
}
