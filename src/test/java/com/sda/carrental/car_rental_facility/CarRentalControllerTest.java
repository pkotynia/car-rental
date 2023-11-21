package com.sda.carrental.car_rental_facility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;


// to jest test integracyjny
//nie uzywamy tu mockow, apliikacja dziala i uzywamy WebTestClient,
// zeby wyslac request i uzyskac response
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CarRentalControllerTest {

    @Autowired
    //WebTestClient allows us to send request and verify responses
    private WebTestClient testClient;

    @Test
    void shouldSaveCarRental() {
        //given - preparing objects that will be used in request body (payload)
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
                //given - request preparation
                .post()
                .uri("/car_rentals")
                .bodyValue(carRental)
                //when - send the request to our app
                .exchange()
               //then - assert response
                .expectStatus()
                .isOk();
    }
}