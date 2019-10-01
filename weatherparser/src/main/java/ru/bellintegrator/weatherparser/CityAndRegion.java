package ru.bellintegrator.weatherparser;

import java.io.Serializable;

public class CityAndRegion implements Serializable {
    String city;
    String region;

    public CityAndRegion(String city, String region) {
        this.city = city;
        this.region = region;
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

    @Override
    public String toString() {
        return "CityAndRegion{" +
                "city='" + city + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
