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
                null,
                "Cars for Rent",
                "www.cars.com",
                "Sosnowiec",
                "Janusz");

        //when
        CarRentalModel result = repository.save(carRental);

        //then
        assertEquals("Sosnowiec", result.getAddress());
        assertEquals("Janusz", result.getOwner());


    }

}