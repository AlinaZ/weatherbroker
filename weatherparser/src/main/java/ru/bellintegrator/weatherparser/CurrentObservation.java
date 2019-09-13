package ru.bellintegrator.weatherparser;

import java.io.Serializable;


public class CurrentObservation implements Serializable{

    //current_observation":
    // {"wind":{"chill":64,"direction":155,"speed":8.08},
    // "atmosphere":{"humidity":78,"visibility":10.0,"pressure":28.82,"rising":0},
    // "astronomy":{"sunrise":"6:28 am","sunset":"8:00 pm"},
    // "condition":{"text":"Mostly Cloudy","code":27,"temperature":64},
    // "pubDate":1566802800}

    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Condition condition;
    private String pubDate;

    public CurrentObservation() {
    }

    public CurrentObservation(Wind wind, Atmosphere atmosphere, Astronomy astronomy, Condition condition, String pubDate) {
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
        this.condition = condition;
        this.pubDate = pubDate;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "CurrentObservation{" +
                "wind='" + wind + '\'' +
                ", atmosphere='" + atmosphere + '\'' +
                ", astronomy='" + astronomy + '\'' +
                ", condition='" + condition + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
