package com.sda.carrental.car_rental_facility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarRentalControllerTest {

    @Autowired
    private WebTestClient testClient;

    @Test
    void shouldSaveCarRental() {
        List<CompanyBranchModel> branches = List.of(
            new CompanyBranchModel(null, "Radom")
        );
        CarRentalModel carRental = new CarRentalModel(
                null,
                "Car Rent",
                "www.cars.pl",
                "Warszawa",
                "Janusz",
                branches

        );
        testClient
                //given
                .post()
                .uri("/car_rentals")
                .bodyValue(carRental)
                //when
                .exchange()
                //then
                .expectStatus()
                .isOk();
    }

}