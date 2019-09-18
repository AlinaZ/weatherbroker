package ru.bellintegrator.db_service.service.currentobservation;

import ru.bellintegrator.db_service.model.*;
import ru.bellintegrator.weatherparser.CurrentObservation;
import ru.bellintegrator.weatherparser.Location;

public interface CurrentObservationService {
    void saveCurrentObservation(String pubDate, Location locationJson);

    CurrentObservation mapEntityToView(CurrentObservationEntity currentObservationEntity);
}
