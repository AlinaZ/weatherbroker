package ru.bellintegrator.db_service.service.forecast;

import ru.bellintegrator.db_service.model.ForecastEntity;
import ru.bellintegrator.db_service.model.LocationEntity;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.Forecast;

public interface ForecastService extends WeatherElementService<Forecast, ForecastEntity, LocationEntity> {
}
