package com.fleetmanagement.Api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trajectories")
public class TrajectoriesModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "taxi_id")
    private TaxisModels taxi;


    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;


    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public TaxisModels getTaxi() {
        return taxi;
    }
    public void setTaxi(TaxisModels taxi) {
        this.taxi = taxi;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
