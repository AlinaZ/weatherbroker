package ru.bellintegrator.weatherparser;

import java.io.Serializable;

public class Atmosphere implements Serializable {
    //"atmosphere":{"humidity":78,"visibility":10.0,"pressure":28.82,"rising":0}

    private Integer humidity;
    private Float visibility;
    private Float pressure;
    private Integer rising;

    public Atmosphere() {
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getVisibility() {
        return visibility;
    }

    public void setVisibility(Float visibility) {
        this.visibility = visibility;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
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
