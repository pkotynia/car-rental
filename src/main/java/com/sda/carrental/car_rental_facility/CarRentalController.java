package com.sda.carrental.car_rental_facility;

import org.springframework.web.bind.annotation.*;

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

    //    znajdujemy obiekt po id w wypozyczalni i jak nie bedzie, to zwracamy error code
    //    obsługa błędów, metoda w service. sciaganie ze sciezki url(PathVariable)
    @GetMapping("/{id}")
    public CarRentalModel getById(@PathVariable Long id) {
        return service.getById(id);
    }


}
