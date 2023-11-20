package com.sda.carrental.car_rental_facility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchesRepository extends JpaRepository<CompanyBranchModel, Long> {

    Optional<Object> findByName(String endBranchName);


}
