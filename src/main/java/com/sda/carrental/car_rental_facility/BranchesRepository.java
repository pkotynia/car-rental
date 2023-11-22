package com.sda.carrental.car_rental_facility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchesRepository extends JpaRepository<BranchesModel, Long> {

    Optional<BranchesModel> findByName(String endBranchName);


}
