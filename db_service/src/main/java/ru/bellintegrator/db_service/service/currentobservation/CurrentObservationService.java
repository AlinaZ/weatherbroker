package ru.bellintegrator.db_service.service.currentobservation;

import ru.bellintegrator.db_service.model.*;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.CurrentObservation;
import ru.bellintegrator.weatherparser.Location;

public interface CurrentObservationService extends WeatherElementService<CurrentObservation, CurrentObservationEntity, LocationEntity> {
}
