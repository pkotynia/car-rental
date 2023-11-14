package com.sda.carrental.car_rental_facility;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "company_branch")
public class CompanyBranchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "name field can't be null")
    private String name;

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
}
