package com.sda.carrental.car_rental_facility;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "company_branch")
public class CompanyBranchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field can't be null")
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_rental_id", nullable = false)
    @JsonBackReference
    private CarRentalModel carRental;

    public CompanyBranchModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyBranchModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarRentalModel getCarRental() {
        return carRental;
    }

    public void setCarRental(CarRentalModel carRental) {
        this.carRental = carRental;
    }

    @Override
    public String toString() {
        return "CompanyBranchModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
