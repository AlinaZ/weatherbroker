package ru.bellintegrator.weatherparser;

import java.io.Serializable;

public class Condition implements Serializable {
    //"condition":{"text":"Mostly Cloudy","code":27,"temperature":64}

    private String text;
    private Integer code;
    private Integer temperature;

    public Condition() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "text='" + text + '\'' +
                ", code='" + code + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
