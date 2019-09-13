package ru.bellintegrator.db_service.service.currentobservation;

import ru.bellintegrator.db_service.dao.astronomy.ResultDao;
import ru.bellintegrator.db_service.model.*;
import ru.bellintegrator.weatherparser.Location;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CurrentObservationServiceImpl implements CurrentObservationService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveCurrentObservation(String pubDate, Location locationJson) {
        CurrentObservationEntity currentObservationEntity=new CurrentObservationEntity();

        currentObservationEntity.setPubDate(pubDate);
        LocationEntity locationEntity=dao.loadLocationByWoeid(locationJson.getWoeid());


        currentObservationEntity.setLocation(locationEntity);

        /*currentObservationEntity.setAstronomy(astronomyEntity);
        currentObservationEntity.setCondition(conditionEntity);
        currentObservationEntity.setAtmosphere(atmosphereEntity);
        currentObservationEntity.setWind(windEntity);*/

        dao.saveCurrentObservation(currentObservationEntity);

    }
}
