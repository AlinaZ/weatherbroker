package ru.bellintegrator.db_service.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Current_observation")
public class CurrentObservationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @OneToOne(mappedBy = "currentObservationEntity", cascade = CascadeType.ALL, optional = false)
    private WindEntity windEntity;

    @OneToOne(mappedBy = "currentObservationEntity", cascade = CascadeType.ALL, optional = false)
    private AtmosphereEntity atmosphere;

    @OneToOne(mappedBy = "currentObservationEntity", cascade = CascadeType.ALL, optional = false)
    private AstronomyEntity astronomyEntity;

    @OneToOne(mappedBy = "currentObservationEntity", cascade = CascadeType.ALL, optional = false)
    private ConditionEntity conditionEntity;

    @Column(name = "pub_date")
    private String pubDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;

    public CurrentObservationEntity() {
    }

    public CurrentObservationEntity(LocationEntity location, String pubDate) {
        this.locationEntity = location;
        this.pubDate = pubDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WindEntity getWind() {
        return windEntity;
    }

    public void setWind(WindEntity wind) {
        this.windEntity = wind;
    }

    public AtmosphereEntity getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(AtmosphereEntity atmosphere) {
        this.atmosphere = atmosphere;
    }

    public AstronomyEntity getAstronomy() {
        return astronomyEntity;
    }

    public void setAstronomy(AstronomyEntity astronomy) {
        this.astronomyEntity = astronomy;
    }

    public ConditionEntity getCondition() {
        return conditionEntity;
    }

    public void setCondition(ConditionEntity condition) {
        this.conditionEntity = condition;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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
        CurrentObservationEntity that = (CurrentObservationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(windEntity, that.windEntity) &&
                Objects.equals(atmosphere, that.atmosphere) &&
                Objects.equals(astronomyEntity, that.astronomyEntity) &&
                Objects.equals(conditionEntity, that.conditionEntity) &&
                Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(locationEntity, that.locationEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, windEntity, atmosphere, astronomyEntity, conditionEntity, pubDate, locationEntity);
    }

    @Override
    public String toString() {
        return "CurrentObservation{" +
                "id=" + id +
                ", wind=" + windEntity +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomyEntity +
                ", condition=" + conditionEntity +
                ", pubDate=" + pubDate +
                '}';
    }
}
