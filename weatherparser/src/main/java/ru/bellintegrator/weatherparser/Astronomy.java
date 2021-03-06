package ru.bellintegrator.weatherparser;

import java.io.Serializable;

public class Astronomy extends WeatherElement implements Serializable {
    //"astronomy":{"sunrise":"6:28 am","sunset":"8:00 pm"}

    private String sunrise;
    private String sunset;

    public Astronomy() {
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Astronomy{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
