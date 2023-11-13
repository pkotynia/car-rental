package com.sda.carrental.car_rental_facility;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarRentalRepositoryTest {

    @Autowired
    private CarRentalRepository repository;

    @Test
    void shouldSaveCarRentalObject() {

        //given
        CompanyBranchModel branch1 = new CompanyBranchModel(null, "Kielce");
        CompanyBranchModel branch2 = new CompanyBranchModel(null, "Poznań");

        List<CompanyBranchModel> branches = List.of(branch1, branch2);
        CarRentalModel carRental = new CarRentalModel(
                null, "Car for Rent", "www.cars.com",
                "Warszawa", "Jan Kowalski", branches);

        //when
//        repository.save(carRental);
        CarRentalModel result = repository.save(carRental);

        //then
//        assertEquals(1, repository.count());
        assertEquals("Warszawa", result.getAddress());
        assertEquals("Jan Kowalski", result.getOwner());
        assertEquals(2, result.getBranches().size());
    }

    @Test
    void shouldCountCarRentals(){

        //given

        //when
        long count = repository.count();

        //then
        assertEquals(0,count);
    }
}