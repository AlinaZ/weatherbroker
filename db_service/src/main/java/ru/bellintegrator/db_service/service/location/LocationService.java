package ru.bellintegrator.db_service.service.location;

import ru.bellintegrator.db_service.model.LocationEntity;
import ru.bellintegrator.weatherparser.Location;

public interface LocationService {
    void saveLocation(Location location);

    Location mapEntityToView(LocationEntity locationEntity);
}
