package ru.bellintegrator.db_service.service.atmosphere;

import ru.bellintegrator.db_service.model.AtmosphereEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.Atmosphere;

public interface AtmosphereService extends WeatherElementService<Atmosphere, AtmosphereEntity, CurrentObservationEntity> {

}
