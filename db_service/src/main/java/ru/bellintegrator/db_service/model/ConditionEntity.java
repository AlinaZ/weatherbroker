package ru.bellintegrator.db_service.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Conditio")
public class ConditionEntity extends BaseEntity implements Serializable {

    @Id
    private Integer id;

    @Version
    private Integer version;

    @Column(name = "text", length = 25)
    private String text;

    @Column(name = "code")
    private Integer code;

    @Column(name = "temperature")
    private Integer temperature;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cur_obs_id")
    private CurrentObservationEntity currentObservationEntity;

    public ConditionEntity() {
    }

    public ConditionEntity(String text, Integer code, Integer temperature, CurrentObservationEntity currentObservation) {
        this.text = text;
        this.code = code;
        this.temperature = temperature;
        this.currentObservationEntity = currentObservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        ConditionEntity condition = (ConditionEntity) o;
        return Objects.equals(id, condition.id) &&
                Objects.equals(text, condition.text) &&
                Objects.equals(code, condition.code) &&
                Objects.equals(temperature, condition.temperature);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, code, temperature);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", code=" + code +
                ", temperature=" + temperature +
                '}';
    }
}
