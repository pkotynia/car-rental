package com.sda.carrental.car_rental_facility;

import org.springframework.stereotype.Service;

@Service
public class CarRentalService {

    private CarRentalRepository repository;

    public CarRentalService(CarRentalRepository repository) {
        this.repository = repository;
    }

    CarRentalModel save (CarRentalModel carRentalModel) {
    return repository.save(carRentalModel);
    }
}
