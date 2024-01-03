package com.fleetmanagement.Api.models;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "taxis")
public class TaxisModels implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String plate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
