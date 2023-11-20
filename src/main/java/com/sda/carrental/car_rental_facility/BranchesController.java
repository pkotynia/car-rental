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
    public List<CompanyBranchDTO> findAll() {
        return service.findAll()
                .stream()
                .map(companyBranchModel -> mapToCompanyBranchDTO(companyBranchModel))
                .toList();
    }

    @GetMapping("/{id}")
    public CompanyBranchDTO findById(@PathVariable Long id) {
        CompanyBranchModel companyBranch = service.findById(id);
        return mapToCompanyBranchDTO(companyBranch);
    }

    private CompanyBranchDTO mapToCompanyBranchDTO(CompanyBranchModel companyBranch) {
        CarRentalModel carRental = companyBranch.getCarRental();
        return new CompanyBranchDTO(
                companyBranch.getId(),
                companyBranch.getName(),
                new HQDetails(
                        carRental.getName(),
                        carRental.getOwner(),
                        carRental.getInternetDomain(),
                        carRental.getAddress()
                )
        );
    }
}

record CompanyBranchDTO(Long branchId, String branchName, HQDetails mainBranchDetails) {
}

record HQDetails(String carRentalName, String owner, String internetDomain, String address) {
}