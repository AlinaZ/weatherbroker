package ru.bellintegrator.db_service.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Местоположение, город
 */
@Entity
@Table(name="Location")
public class LocationEntity implements Serializable {

    @Id
    @Column(name = "woeid", unique = true)
    private Integer woeid;

    @Version
    private Integer version;

    @Column(name = "city", length = 25)
    private String city;

    @Column(name = "region", length = 25)
    private String region;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lon")
    private Double longitude;

    @Column(name = "timezone_id")
    private String timezone;

    @OneToMany(mappedBy = "locationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrentObservationEntity> currentObservationEntities;

    @OneToMany(mappedBy = "locationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForecastEntity> forecastEntities;

    public LocationEntity() {
    }

    public LocationEntity(Integer woeid, String city, String region, String country, Double latitude, Double longitude, String timezone) {
        this.woeid = woeid;
        this.city = city;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<CurrentObservationEntity> getCurrentObservations() {
        if (currentObservationEntities == null) {
            currentObservationEntities = new ArrayList<>();
        }
        return currentObservationEntities;
    }

    public void setCurrentObservations(List<CurrentObservationEntity> currentObservations) {
        this.currentObservationEntities = currentObservations;
    }

    public void addCurrentObservations(CurrentObservationEntity currentObservation) {
        getCurrentObservations().add(currentObservation);
        currentObservation.setLocation(this);
    }

    public void removeCurrentObservations(CurrentObservationEntity currentObservation) {
        getCurrentObservations().remove(currentObservation);
        currentObservation.setLocation(null);
    }

    public List<ForecastEntity> getForecasts() {
        if (forecastEntities == null) {
            forecastEntities = new ArrayList<>();
        }
        return forecastEntities;
    }

    public void setForecasts(List<ForecastEntity> forecasts) {
        this.forecastEntities = forecasts;
    }

    public void addForecast(ForecastEntity forecast) {
        getForecasts().add(forecast);
        forecast.setLocation(this);
    }

    public void removeForecast(ForecastEntity forecast) {
        getForecasts().remove(forecast);
        forecast.setLocation(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationEntity location = (LocationEntity) o;
        return Objects.equals(woeid, location.woeid) &&
                Objects.equals(city, location.city) &&
                Objects.equals(region, location.region) &&
                Objects.equals(country, location.country) &&
                Objects.equals(latitude, location.latitude) &&
                Objects.equals(longitude, location.longitude) &&
                Objects.equals(timezone, location.timezone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(woeid, city, region, country, latitude, longitude, timezone);
    }

    @Override
    public String toString() {
        return "Location{" +
                "woeid=" + woeid +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone=" + timezone +
                '}';
    }
}
