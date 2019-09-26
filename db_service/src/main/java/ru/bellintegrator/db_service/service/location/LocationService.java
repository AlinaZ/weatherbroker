package ru.bellintegrator.db_service.service.location;

import ru.bellintegrator.db_service.model.LocationEntity;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.Location;

public interface LocationService extends WeatherElementService<Location, LocationEntity, LocationEntity> {
}
