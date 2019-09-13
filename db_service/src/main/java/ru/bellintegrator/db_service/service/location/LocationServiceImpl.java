package ru.bellintegrator.db_service.service.location;

import ru.bellintegrator.db_service.dao.astronomy.ResultDao;
import ru.bellintegrator.weatherparser.Location;
import ru.bellintegrator.db_service.model.LocationEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class LocationServiceImpl implements LocationService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveLocation(Location location){
        LocationEntity locationEntity =new LocationEntity();
        locationEntity.setWoeid(location.getWoeid());
        locationEntity.setCity(location.getCity());
        locationEntity.setRegion(location.getRegion());


        locationEntity.setCountry(location.getCountry());
        locationEntity.setLatitude(location.getLatitude());
        locationEntity.setLongitude(location.getLongitude());

        System.out.println("********************************************************************"+locationEntity+"*****************************************************");
        dao.saveLocation(locationEntity);
    }






}
