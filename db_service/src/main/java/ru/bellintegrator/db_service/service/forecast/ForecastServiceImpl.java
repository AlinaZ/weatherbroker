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
    public void saveForecasts(List<Forecast> forecastsJson, Location locationJson) {

        for (Forecast oneDayForecast : forecastsJson) {
            ForecastEntity forecastEntity=new ForecastEntity();
            forecastEntity.setCode(oneDayForecast.getCode());
            forecastEntity.setDate(oneDayForecast.getDate());
            forecastEntity.setDay(oneDayForecast.getDay());
            forecastEntity.setHigh(oneDayForecast.getHigh());
            forecastEntity.setLow(oneDayForecast.getLow());
            forecastEntity.setText(oneDayForecast.getText());

            LocationEntity locationEntity=dao.loadLocationByWoeid(locationJson.getWoeid());

            forecastEntity.setLocation(locationEntity);

            //сохранение в базу данных
            dao.saveForecast(forecastEntity);

        }
    }

    @Override
    public Forecast mapForecastEntityToView(ForecastEntity forecastEntity){
        Forecast forecastView=new Forecast();
        forecastView.setDate(forecastEntity.getDate());
        forecastView.setDay(forecastEntity.getDay());
        forecastView.setText(forecastEntity.getText());
        forecastView.setHigh(forecastEntity.getHigh());
        forecastView.setLow(forecastEntity.getLow());
        forecastView.setCode(forecastEntity.getCode());
        return forecastView;
    }


}
