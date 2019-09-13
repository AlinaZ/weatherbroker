package ru.bellintegrator.db_service.model;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Objects;

/**
 * Текущая информация о ветре
 */
@Entity
@Table(name="Wind")
public class WindEntity implements Serializable {


    @Id
    private Integer id;


    @Version
    private Integer version;


    @Column(name = "chill")
    private Integer chill;


    @Column(name = "direction")
    private Integer direction;


    @Column(name = "speed")
    private Integer speed;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservationEntity currentObservationEntity;

    public WindEntity() {
    }

    public WindEntity(Integer chill, Integer direction, Integer speed, CurrentObservationEntity currentObservation) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
        this.currentObservationEntity = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        WindEntity wind = (WindEntity) o;
        return Objects.equals(id, wind.id) &&
                Objects.equals(chill, wind.chill) &&
                Objects.equals(direction, wind.direction) &&
                Objects.equals(speed, wind.speed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, chill, direction, speed);
    }

    @Override
    public String toString() {
        return "Wind{" +
                "id=" + id +
                ", chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
