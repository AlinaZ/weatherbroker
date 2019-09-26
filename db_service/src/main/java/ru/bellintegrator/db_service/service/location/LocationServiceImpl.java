package ru.bellintegrator.db_service.service.location;

import ru.bellintegrator.db_service.dao.ResultDao;
import ru.bellintegrator.weatherparser.Location;
import ru.bellintegrator.db_service.model.LocationEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class LocationServiceImpl implements LocationService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveElement(Location location,LocationEntity unusedParameter) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setWoeid(location.getWoeid());
        locationEntity.setCity(location.getCity());
        locationEntity.setRegion(location.getRegion());
        locationEntity.setCountry(location.getCountry());
        locationEntity.setLatitude(location.getLatitude());
        locationEntity.setLongitude(location.getLongitude());
        dao.saveLocation(locationEntity);
    }

    @Override
    public Location mapEntityToView(LocationEntity locationEntity) {
        if (locationEntity == null) {
            return null;
        } else {
            Location locationView = new Location();
            locationView.setCity(locationEntity.getCity());
            locationView.setCountry(locationEntity.getCountry());
            locationView.setRegion(locationEntity.getRegion());
            locationView.setWoeid(locationEntity.getWoeid());
            locationView.setLatitude(locationEntity.getLatitude());
            locationView.setLongitude(locationEntity.getLongitude());
            return locationView;
        }
    }


}
