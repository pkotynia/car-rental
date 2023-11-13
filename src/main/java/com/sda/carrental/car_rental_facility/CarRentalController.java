package com.sda.carrental.car_rental_facility;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car_rentals")
public class CarRentalController {

    private CarRentalService service;

    public CarRentalController(CarRentalService service) {
        this.service = service;
    }

    @PostMapping
    public CarRentalModel save(@RequestBody @Valid CarRentalModel carRentalModel){
        return service.save(carRentalModel);
    }

    @GetMapping("/{id}")
    public CarRentalModel getById(@PathVariable Long id){
        return service.getById(id);
    }

}
