package ru.bellintegrator.db_service.service.atmosphere;

import ru.bellintegrator.db_service.dao.ResultDao;
import ru.bellintegrator.db_service.model.AtmosphereEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Atmosphere;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AtmosphereServiceImpl implements AtmosphereService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveAtmosphere(Atmosphere atmosphereJson, CurrentObservationEntity currentObservationEntity){
        AtmosphereEntity atmosphereEntity=new AtmosphereEntity();
        atmosphereEntity.setHumidity(atmosphereJson.getHumidity());
        atmosphereEntity.setVisibility(atmosphereJson.getVisibility());
        atmosphereEntity.setPressure(atmosphereJson.getPressure());
        atmosphereEntity.setRising(atmosphereJson.getRising());
        atmosphereEntity.setCurrentObservation(currentObservationEntity);
        dao.saveAtmosphere(atmosphereEntity);
    }

    @Override
    public Atmosphere mapEntityToView(AtmosphereEntity atmosphereEntity){
        Atmosphere atmosphereView=new Atmosphere();
        atmosphereView.setPressure(atmosphereEntity.getPressure());
        atmosphereView.setHumidity(atmosphereEntity.getHumidity());
        atmosphereView.setVisibility(atmosphereEntity.getVisibility());
        atmosphereView.setRising(atmosphereEntity.getRising());
        return atmosphereView;
    }
}
