package ru.bellintegrator.db_service.service.wind;

import ru.bellintegrator.db_service.dao.astronomy.ResultDao;
import ru.bellintegrator.db_service.model.WindEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Wind;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class WindServiceImpl implements WindService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveWind(Wind windJson, CurrentObservationEntity currentObservationEntity) {
        WindEntity windEntity = new WindEntity();
        windEntity.setChill(windJson.getChill());
        windEntity.setDirection(windJson.getDirection());
        windEntity.setSpeed(windJson.getSpeed());


        windEntity.setCurrentObservation(currentObservationEntity);

        dao.saveWind(windEntity);

    }
}
