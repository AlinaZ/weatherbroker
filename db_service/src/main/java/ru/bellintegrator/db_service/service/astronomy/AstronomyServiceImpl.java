package ru.bellintegrator.db_service.service.astronomy;

import ru.bellintegrator.db_service.dao.astronomy.ResultDao;
import ru.bellintegrator.db_service.model.AstronomyEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Astronomy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AstronomyServiceImpl implements AstronomyService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveAstronomy(Astronomy astronomyJson, CurrentObservationEntity currentObservationEntity){
        AstronomyEntity astronomyEntity=new AstronomyEntity();
        astronomyEntity.setSunrise(astronomyJson.getSunrise());
        astronomyEntity.setSunset(astronomyJson.getSunset());
        astronomyEntity.setCurrentObservation(currentObservationEntity);
        dao.saveAstronomy(astronomyEntity);
    }


}
