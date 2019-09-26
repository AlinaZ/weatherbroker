package ru.bellintegrator.db_service.service.forecast;

import ru.bellintegrator.db_service.dao.ResultDao;
import ru.bellintegrator.db_service.model.ForecastEntity;
import ru.bellintegrator.db_service.model.LocationEntity;
import ru.bellintegrator.weatherparser.Forecast;
import ru.bellintegrator.weatherparser.Location;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ForecastServiceImpl implements ForecastService {

    @Inject
    private ResultDao dao;



    @Override
    public void saveElement(Forecast oneDayForecast, LocationEntity locationEntity) {
            ForecastEntity forecastEntity = new ForecastEntity();
            forecastEntity.setCode(oneDayForecast.getCode());
            forecastEntity.setDate(oneDayForecast.getDate());
            forecastEntity.setDay(oneDayForecast.getDay());
            forecastEntity.setHigh(oneDayForecast.getHigh());
            forecastEntity.setLow(oneDayForecast.getLow());
            forecastEntity.setText(oneDayForecast.getText());
            forecastEntity.setLocation(locationEntity);
            dao.saveForecast(forecastEntity);
    }

    @Override
    public Forecast mapEntityToView(ForecastEntity forecastEntity) {
        if (forecastEntity == null) {
            return null;
        } else {
            Forecast forecastView = new Forecast();
            forecastView.setDate(forecastEntity.getDate());
            forecastView.setDay(forecastEntity.getDay());
            forecastView.setText(forecastEntity.getText());
            forecastView.setHigh(forecastEntity.getHigh());
            forecastView.setLow(forecastEntity.getLow());
            forecastView.setCode(forecastEntity.getCode());
            return forecastView;
        }
    }


}
