package ru.bellintegrator.db_service.dao.astronomy;

import ru.bellintegrator.db_service.model.*;

public interface ResultDao {
    void saveAstronomy(AstronomyEntity astronomy);

    void saveAtmosphere(AtmosphereEntity atmosphere);

    void saveWind(WindEntity wind);

    void saveCondition(ConditionEntity condition);

    void saveCurrentObservation(CurrentObservationEntity currentObservation);

    void saveForecast(ForecastEntity forecast);

    void saveLocation(LocationEntity location);

    LocationEntity loadLocationByWoeid(Integer woeid);

    CurrentObservationEntity loadByLocationAndDate(Integer woeid, String date);
}
