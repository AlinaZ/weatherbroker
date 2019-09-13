package ru.bellintegrator.db_service.service.forecast;

import ru.bellintegrator.weatherparser.Forecast;
import ru.bellintegrator.weatherparser.Location;

import java.util.List;

public interface ForecastService {
    void saveForecasts(List<Forecast> forecastsJson, Location locationJson);
}
