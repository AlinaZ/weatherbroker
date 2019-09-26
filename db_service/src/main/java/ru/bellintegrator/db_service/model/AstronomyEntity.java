package ru.bellintegrator.db_service.model;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;

import java.util.Objects;

/**
 * Информация о текущих астрономических условиях
 */
//@ApplicationScoped
@Entity
@Table(name="Astronomy")
public class AstronomyEntity extends BaseEntity implements Serializable {

   @Id
    private Integer id;


    @Version
    private Integer version;


    @Column(name = "sunrise", length = 25)
    private String sunrise;


    @Column(name = "sunset", length = 25)
    private String sunset;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservationEntity currentObservationEntity;

    public AstronomyEntity() {
    }

    public AstronomyEntity(String sunrise, String sunset, CurrentObservationEntity currentObservation) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.currentObservationEntity = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        AstronomyEntity astronomy = (AstronomyEntity) o;
        return Objects.equals(id, astronomy.id) &&
                Objects.equals(sunrise, astronomy.sunrise) &&
                Objects.equals(sunset, astronomy.sunset);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sunrise, sunset);
    }

    @Override
    public String toString() {
        return "Astronomy{" +
                "id=" + id +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
