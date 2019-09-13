package ru.bellintegrator.yahoo_weather.parser;

import java.io.Serializable;

public class Atmosphere implements Serializable {
    //"atmosphere":{"humidity":78,"visibility":10.0,"pressure":28.82,"rising":0}

    private String humidity;
    private String visibility;
    private String pressure;
    private String rising;

    public Atmosphere() {
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRising() {
        return rising;
    }

    public void setRising(String rising) {
        this.rising = rising;
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "humidity='" + humidity + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pressure='" + pressure + '\'' +
                ", rising='" + rising + '\'' +
                '}';
    }
}
