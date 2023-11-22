package com.sda.carrental.car_rental_facility;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    private final BranchesService service;

    public BranchesController(BranchesService service) {
        this.service = service;
    }

    @GetMapping
    public List<BranchesDTO> findAll() {
        return service.findAll()
                .stream()
                .map(companyBranchModel -> mapToCompanyBranchDTO(companyBranchModel))
                .toList();
    }

    @GetMapping("/{id}")
    public BranchesDTO findById(@PathVariable Long id) {
        BranchesModel companyBranch = service.findById(id);
        return mapToCompanyBranchDTO(companyBranch);
    }

    private BranchesDTO mapToCompanyBranchDTO(BranchesModel companyBranch) {
        CarRentalModel carRental = companyBranch.getCarRental();
        return new BranchesDTO(
                companyBranch.getId(),
                companyBranch.getName(),
                new BranchesHQDetails(
                        carRental.getName(),
                        carRental.getOwner(),
                        carRental.getInternetDomain(),
                        carRental.getAddress()
                )
        );
    }
}

