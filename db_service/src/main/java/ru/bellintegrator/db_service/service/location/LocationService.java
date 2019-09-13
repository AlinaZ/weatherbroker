package ru.bellintegrator.db_service.service.location;

import ru.bellintegrator.weatherparser.Location;

public interface LocationService {
    void saveLocation(Location location);
}
