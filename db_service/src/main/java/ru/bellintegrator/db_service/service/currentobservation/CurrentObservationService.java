package ru.bellintegrator.db_service.service.currentobservation;

import ru.bellintegrator.weatherparser.Location;

public interface CurrentObservationService {
    void saveCurrentObservation(String pubDate, Location locationJson);
}
