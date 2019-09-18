package ru.bellintegrator.db_service.service.astronomy;

import ru.bellintegrator.db_service.model.AstronomyEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Astronomy;

public interface AstronomyService {
    void saveAstronomy(Astronomy astronomyJson, CurrentObservationEntity currentObservationEntity);

    Astronomy mapEntityToView(AstronomyEntity astronomyEntity);
}
