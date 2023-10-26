package com.sda.carrental.car_rental_facility;

import jakarta.persistence.*;

@Entity
@Table(name = "company_branch")
public class CompanyBranchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_rental_id")
    private CarRentalModel carRentalModel;

    public CompanyBranchModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyBranchModel() {
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

    @Override
    public String toString() {
        return "CompanyBranchModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
