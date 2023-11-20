package com.sda.carrental.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import com.sda.carrental.car_rental_facility.BranchesRepository;
import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private BranchesRepository branchesRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testSaveReservation() {
        // Mock data
        CompanyBranchModel startBranch = new CompanyBranchModel(1L, "StartBranch");
        CompanyBranchModel endBranch = new CompanyBranchModel(2L, "EndBranch");

        ReservationDTO reservationDTO = new ReservationDTO(
                "John Doe",
                1L,
                LocalDate.parse("2023-01-01"),
                LocalDate.parse("2023-01-10"),
                startBranch,
                endBranch
        );

        CarModel mockCar = new CarModel();

        // Mock repository calls
        Mockito.when(branchesRepository.findByName("EndBranch")).thenReturn(Optional.of(endBranch));
        Mockito.when(branchesRepository.findByName("StartBranch")).thenReturn(Optional.of(new CompanyBranchModel()));
        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(mockCar));
        Mockito.when(reservationRepository.save(Mockito.any(ReservationModel.class))).thenReturn(new ReservationModel());

        // ArgumentCaptor for ReservationModel
        ArgumentCaptor<ReservationModel> captor = ArgumentCaptor.forClass(ReservationModel.class);

        // Call the service method
        ReservationModel result = reservationService.saveReservation(reservationDTO);

        // Verify the save method is called once with the captured argument
        Mockito.verify(reservationRepository, Mockito.times(1)).save(captor.capture());

        // Retrieve the captured ReservationModel
        ReservationModel capturedReservation = captor.getValue();

        // Assertions
        Mockito.verify(branchesRepository, Mockito.times(2)).findByName(Mockito.anyString()); // Ensure findByName is called twice
        Mockito.verify(carRepository, Mockito.times(1)).findById(1L); // Ensure findById is called once

        // Add more assertions based on your business logic and expectations
        assertThat(capturedReservation.getPrice()).isEqualTo(BigDecimal.valueOf(100));
    }
}
