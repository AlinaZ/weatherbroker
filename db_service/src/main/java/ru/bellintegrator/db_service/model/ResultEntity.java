package ru.bellintegrator.db_service.model;

import java.io.Serializable;
import java.util.List;

public class ResultEntity implements Serializable {

    public ResultEntity() {
    }

    public ResultEntity(LocationEntity location, CurrentObservationEntity currentObservation, List<ForecastEntity> forecasts) {
        this.locationEntity = location;
        this.currentObservationEntity = currentObservation;
        this.forecastEntities = forecasts;
    }


    private LocationEntity locationEntity;

    public LocationEntity getLocation() {
        return locationEntity;
    }

    public void setLocation(LocationEntity location) {
        this.locationEntity = location;
    }


    private CurrentObservationEntity currentObservationEntity;

    public CurrentObservationEntity getCurrentObservation() {
        return currentObservationEntity;
    }

    public void setCurrentObservation(CurrentObservationEntity currentObservation) {
        this.currentObservationEntity = currentObservation;
    }


    private List<ForecastEntity> forecastEntities;

    public List<ForecastEntity> getForecasts() {
        return forecastEntities;
    }

    public void setForecasts(List<ForecastEntity> forecasts) {
        this.forecastEntities = forecasts;
    }

    @Override
    public String toString() {
        return "Result{" +
                "location=" + locationEntity +
                ", currentObservation=" + currentObservationEntity +
                ", forecasts=" + forecastEntities +
                '}';
    }
}
