package ru.bellintegrator.db_service.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Прогноз погоды
 */
@Entity
@Table(name="Forecast")
public class ForecastEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "day", length = 3)
    private String day;

    @Column(name = "date",length = 20)
    private String date;

    @Column(name = "low")
    private Integer low;

    @Column(name = "high")
    private Integer high;

    @Column(name = "text", length = 25)
    private String text;

    @Column(name = "code")
    private Integer code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;

    public ForecastEntity() {
    }

    public ForecastEntity(String day, String date, Integer low, Integer high, String text, Integer code) {
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocationEntity getLocation() {
        return locationEntity;
    }

    public void setLocation(LocationEntity location) {
        this.locationEntity = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastEntity forecast = (ForecastEntity) o;
        return Objects.equals(id, forecast.id) &&
                Objects.equals(version, forecast.version) &&
                Objects.equals(day, forecast.day) &&
                Objects.equals(date, forecast.date) &&
                Objects.equals(low, forecast.low) &&
                Objects.equals(high, forecast.high) &&
                Objects.equals(text, forecast.text) &&
                Objects.equals(code, forecast.code) &&
                Objects.equals(locationEntity, forecast.locationEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, day, date, low, high, text, code, locationEntity);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", date=" + date +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }
}
