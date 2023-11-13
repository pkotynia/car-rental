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
        CompanyBranchModel branch1 = new CompanyBranchModel(null, "Wroclaw");
        CompanyBranchModel branch2 = new CompanyBranchModel(null, "Poznań");

        List<CompanyBranchModel> branches = List.of(branch1, branch2);

        CarRentalModel carRental = new CarRentalModel(
                null,
                "Cars for Rent",
                "www.cars.com",
                "Wroclaw",
                "Pablo",
                branches);

        //when
        CarRentalModel result = repository.save(carRental);


        //then
        assertEquals("Wroclaw", result.getAddress());
        assertEquals("Pablo", result.getOwner());
        assertEquals(2,result.getBranches().size());

    }

}