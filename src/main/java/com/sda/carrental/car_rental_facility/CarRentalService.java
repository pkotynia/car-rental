package com.sda.carrental.car_rental_facility;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRentalService {

    private CarRentalRepository repository;

    public CarRentalService(CarRentalRepository repository) {
        this.repository = repository;
    }

    CarRentalModel save(CarRentalModel carRentalModel) {
        return repository.save(carRentalModel);
    }

    //domyslny modyfikator dostepu, bez public, bo jestesmy w jednym pakiecie.
    // obsluga wyjatku, wyrzuca wyjatek, jak nie znajdzie obniektu po id
    CarRentalModel getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Car Rental not found"));
          /* Alternatywny kod:
          Optional<CarRentalModel> optionalOfCarRental = repository.findById(id);
        if (optionalOfCarRental.isPresent()) {
           return optionalOfCarRental.get();
       } else {
            throw new ObjectNotFoundInRepositoryException("Car Rental not found");
        }*/
    }
}
