package ru.bellintegrator.db_service.model;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Objects;

/**
 * Информация о текущем атмосферном давлении, влажности и видимости
 */
@Entity
@Table(name="Atmosphere")
public class AtmosphereEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 803745049486954975L;

    /**
     * Уникальный идентификатор
     */
    @Id
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Влажность, в процентах
     */
    @Column(name = "humidity")
    private Integer humidity;

    /**
     * Видимость, в километрах
     */
    @Column(name = "visibility")
    private Float visibility;

    /**
     * Давление, в мбар
     */
    @Column(name = "pressure")
    private Float pressure;

    /**
     * Состояние барометрического давления: устойчивое (0), повышение (1) или падение (2)
     */
    @Column(name = "rising")
    private Integer rising;

    /**
     * Текущий обзор погоды
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservationEntity currentObservationEntity;

    public AtmosphereEntity() {
    }

    public AtmosphereEntity(Integer humidity, Float visibility, Float pressure, Integer rising, CurrentObservationEntity currentObservation) {
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.rising = rising;
        this.currentObservationEntity = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CurrentObservationEntity getCurrentObservation() {
        return currentObservationEntity;
    }

    public void setCurrentObservation(CurrentObservationEntity currentObservation) {
        this.currentObservationEntity = currentObservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmosphereEntity that = (AtmosphereEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(rising, that.rising);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, humidity, visibility, pressure, rising);
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "id=" + id +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }
}
