package ru.bellintegrator.db_service.service.atmosphere;

import ru.bellintegrator.db_service.model.AtmosphereEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Atmosphere;

public interface AtmosphereService {
    void saveAtmosphere(Atmosphere atmosphereJson, CurrentObservationEntity currentObservationEntity);

    Atmosphere mapEntityToView(AtmosphereEntity atmosphereEntity);
}
