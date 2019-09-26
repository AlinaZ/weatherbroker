package ru.bellintegrator.db_service.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.db_service.model.*;
import ru.bellintegrator.weatherparser.Forecast;

import javax.persistence.*;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

    private final Logger LOG = LoggerFactory.getLogger(ResultDaoImpl.class);

    @PersistenceContext(unitName = "1")
    private EntityManager entityManager;


    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAstronomy(AstronomyEntity astronomy) {
        entityManager.persist(astronomy);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void saveAtmosphere(AtmosphereEntity atmosphere) {
        entityManager.persist(atmosphere);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveWind(WindEntity wind) { entityManager.persist(wind);     }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCondition(ConditionEntity condition) {
        entityManager.persist(condition);
    }

    @Override
    public void saveCurrentObservation(CurrentObservationEntity currentObservation) {
        entityManager.persist(currentObservation);
        LOG.info("CURRENT OBSERVATION PERSISTED");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveForecast(ForecastEntity forecast) {
        entityManager.persist(forecast);
        LOG.info("FORECAST PERSISTED");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveLocation(LocationEntity location) {
        if (loadLocationByWoeid(location.getWoeid()) == null) {
            entityManager.persist(location);
            LOG.info("LOCATION PERSISTED");
        } else {
            LOG.info("THIS LOCATION ALREADY EXISTS IN DATABASE");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocationEntity loadLocationByWoeid(Integer woeid) {
        return entityManager.find(LocationEntity.class, woeid);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public LocationEntity loadLocationByCity(String city) {
        TypedQuery<LocationEntity> query = entityManager.createQuery(
                "SELECT loc FROM LocationEntity AS loc WHERE loc.city='" + city + "'", LocationEntity.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrentObservationEntity loadCOByLocationAndDate(Integer woeid, String date) {
        //LocationEntity locationEntity=loadLocationByWoeid(woeid);
        TypedQuery<CurrentObservationEntity> query = entityManager.createQuery(
                "SELECT c FROM CurrentObservationEntity AS c WHERE c.locationEntity=" + woeid + " AND c.pubDate=" + date, CurrentObservationEntity.class);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public CurrentObservationEntity loadLatestCOByLocation(Integer woeid) {
        TypedQuery<CurrentObservationEntity> query = entityManager.createQuery(
                "SELECT c FROM CurrentObservationEntity AS c WHERE c.locationEntity=" + woeid + "ORDER BY c.pubDate DESC", CurrentObservationEntity.class);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ForecastEntity> load10DaysForecastsByCity(Integer woeid) {
        TypedQuery<ForecastEntity> query = entityManager.createQuery(
                "SELECT f FROM ForecastEntity AS f " +
                        "WHERE f.locationEntity=" + woeid + " ORDER BY f.date DESC", ForecastEntity.class);
        query.setMaxResults(10);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


}
