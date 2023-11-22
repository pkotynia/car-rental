package com.sda.carrental.car_rental_facility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
// This is an example of integration test
// We are not using any mocks.
// Application is being run und we use WebTestClient to send the request and assert responses

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CarRentalControllerTest {

    @Autowired
    //WebTestClient allows us to send request and verify responses
    private WebTestClient testClient;


    @Test
    void shouldSaveCarRental() {
        //given - preparing objects that will be used in request body (payload)
        List<BranchesModel> branches = List.of(
                new BranchesModel(null, "Opole")
        );

        CarRentalModel carRental = new CarRentalModel(
                null,
                "Cars for Rent",
                "www.superCars.com",
                "Wroclaw",
                "Pablo",
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