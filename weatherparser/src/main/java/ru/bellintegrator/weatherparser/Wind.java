package ru.bellintegrator.weatherparser;

import java.io.Serializable;

public class Wind implements Serializable {
    //"wind":{"chill":64,"direction":155,"speed":8.08}

    private Integer chill;
    private Integer direction;
    private Integer speed;

    public Wind() {
    }

    public Integer getChill() {
        return chill;
    }

    public void setChill(Integer chill) {
        this.chill = chill;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "chill='" + chill + '\'' +
                ", direction='" + direction + '\'' +
                ", speed='" + speed + '\'' +
                '}';
    }
}
