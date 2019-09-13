package ru.bellintegrator.yahoo_weather.parser;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;


public class Forecast implements Serializable{

    //{"day":"Thu","date":1567054800,"low":53,"high":79,"text":"Sunny","code":32}

    public Forecast() {
    }

    public Forecast(String day, String date, Integer low, Integer high, String text) {
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
    }

    private String date;
    private Integer low;
    private Integer high;
    private String text;
    private String day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
