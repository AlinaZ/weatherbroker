package ru.bellintegrator.yahoo_weather.parser;

import java.io.Serializable;

public class Condition implements Serializable {
    //"condition":{"text":"Mostly Cloudy","code":27,"temperature":64}

    private String text;
    private String code;
    private String temperature;

    public Condition() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
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
