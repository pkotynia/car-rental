package com.sda.carrental.car_rental_facility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRentalRepositoryTest {

    @Autowired
    private CarRentalRepository repository;

    @Test
    void shouldSaveCarRentalObject() {

        //given
        CarRentalModel carRental = new CarRentalModel(
                null, "Car for Rent", "www.cars.com",
                "Warszawa", "Jan Kowalski");

        //when
//        repository.save(carRental);
        CarRentalModel result = repository.save(carRental);


        //then
//        assertEquals(1, repository.count());
        assertEquals("Warszawa", result.getAddress());
    }

}