package com.sda.carrental.car_rental_facility;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "company_branch")
public class BranchesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull(message = "field can't be null")
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_rental_id", nullable = false)
    @JsonBackReference
    private CarRentalModel carRentalModel;

    public BranchesModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BranchesModel() {
    }

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

    public CarRentalModel getCarRentalModel() {
        return carRentalModel;
    }

    public void setCarRentalModel(CarRentalModel carRentalModel) {
        this.carRentalModel = carRentalModel;
    }

    @Override
    public String toString() {
        return "CompanyBranchModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
