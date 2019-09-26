package ru.bellintegrator.db_service.service.wind;

import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.db_service.model.WindEntity;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.Wind;

public interface WindService extends WeatherElementService<Wind, WindEntity, CurrentObservationEntity> {
}
