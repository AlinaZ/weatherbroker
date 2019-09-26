package ru.bellintegrator.db_service.dao;

import ru.bellintegrator.db_service.model.*;

import java.util.List;

public interface ResultDao{

    /*<B extends BaseEntity> void saveEntityToDB  (B entity);*/

    void saveAstronomy(AstronomyEntity astronomy);

    void saveAtmosphere(AtmosphereEntity atmosphere);

    void saveWind(WindEntity wind);

    void saveCondition(ConditionEntity condition);

    void saveCurrentObservation(CurrentObservationEntity currentObservation);

    void saveForecast(ForecastEntity forecast);

    void saveLocation(LocationEntity location);

    LocationEntity loadLocationByWoeid(Integer woeid);

    LocationEntity loadLocationByCity(String city);

    CurrentObservationEntity loadCOByLocationAndDate(Integer woeid, String date);

    CurrentObservationEntity loadLatestCOByLocation(Integer woeid);

    List<ForecastEntity> load10DaysForecastsByCity(Integer woeid);
}
