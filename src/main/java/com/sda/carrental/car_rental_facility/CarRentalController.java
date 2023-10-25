package com.sda.carrental.car_rental_facility;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car_rentals")
public class CarRentalController {

    private CarRentalService service;

    public CarRentalController(CarRentalService service) {
        this.service = service;
    }

    @PostMapping
    public CarRentalModel save(@RequestBody CarRentalModel carRentalModel) {
        return service.save(carRentalModel);
    }
}
