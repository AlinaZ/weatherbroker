package ru.bellintegrator.db_service.service.astronomy;

import ru.bellintegrator.db_service.model.AstronomyEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.Astronomy;

public interface AstronomyService extends WeatherElementService<Astronomy, AstronomyEntity, CurrentObservationEntity>  {

}
