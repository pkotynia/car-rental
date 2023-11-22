package com.sda.carrental.car_rental_facility;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchesService {

    private final BranchesRepository repository;

    public BranchesService(BranchesRepository repository) {
        this.repository = repository;
    }

    BranchesModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch not found"));
    }

    List<BranchesModel> findAll() {
        return repository.findAll();
    }
}
