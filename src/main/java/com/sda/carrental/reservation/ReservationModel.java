package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customer;

    @NotNull
    private String car;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "start_branch_id")
    private CompanyBranchModel startBranch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_branch_id")
    private CompanyBranchModel endBranch;

    public ReservationModel(Long id, String customer, String car, LocalDate startDate, LocalDate endDate, BigDecimal price, CompanyBranchModel startBranch, CompanyBranchModel endBranch) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.startBranch = startBranch;
        this.endBranch = endBranch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CompanyBranchModel getStartBranch() {
        return startBranch;
    }

    public void setStartBranch(CompanyBranchModel startBranch) {
        this.startBranch = startBranch;
    }

    public CompanyBranchModel getEndBranch() {
        return endBranch;
    }

    public void setEndBranch(CompanyBranchModel endBranch) {
        this.endBranch = endBranch;
    }

    @Override
    public String toString() {
        return "ReservationModel{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", car='" + car + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", startBranch=" + startBranch +
                ", endBranch=" + endBranch +
                '}';
    }
}
