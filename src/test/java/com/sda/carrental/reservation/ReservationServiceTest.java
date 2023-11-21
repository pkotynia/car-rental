package com.sda.carrental.reservation;

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

import static org.assertj.core.api.Assertions.assertThat;

//test jednostkowy (unit test). we need to mock all dependencies in order to test only the code from ReservationService
@SpringBootTest
class ReservationServiceTest {

    //this annotation allows us to replace real CarRepository object with a Mock
    //Mock have the same type as real object byt by default all methods will return null
    //we can record mock behavior and make it return objects that we desire
    @Mock
    private CarRepository carRepositoryMock;

    @Mock
    private BranchesRepository branchesRepositoryMock;

    @Mock
    private ReservationRepository reservationRepositoryMock;

    //this annotation will create Reservationservice object and inject Mock created
    // above via constructor
    @InjectMocks
    private ReservationService reservationService;

    @Test
    void shouldSaveReservation() {

        //given
        //this object will be passed as an argument to save method
        ReservationDTO reservationDTO = new ReservationDTO(
                "Ted",
                1L,
                LocalDate.of(2023, 11, 20),
                LocalDate.of(2023, 11, 22),
                1L,
                2L
        );

        //here we are recording the behavior of branchesRepositoryMock
        //when findById will be invoked in the method code we will return startBranch object
        CompanyBranchModel startBranch = new CompanyBranchModel(1L, "Warszawa");
        Mockito.when(branchesRepositoryMock.findById(1L)).thenReturn(Optional.of(startBranch));

        CompanyBranchModel endBranch = new CompanyBranchModel(2L, "Gdynia");
        Mockito.when(branchesRepositoryMock.findById(2L)).thenReturn(Optional.of(endBranch));

        //here we are recording the bahavior of carRepositoryMock mock
        //whenever in production code .findById() will 1L as and argument, method will be
        // called, car object will be returned
        CarModel car = new CarModel(
                1L,
                "KIA",
                "Ceed",
                "sedan",
                CarStatus.AVAILABLE,
                BigDecimal.valueOf(100)
        );

        Mockito.when(carRepositoryMock.findById(1L)).thenReturn(Optional.of(car));

        //when
        //here we are calling method on our Object under test
        //we will not able to do any assertions on returned value because in our
        // production code last operation is return reservationRepository.save(reservation)
        // - and we have mocked reservationRepository.
        //we are checking if our code works properly, verifying the argument passed to reservationRepository mock
        reservationService.saveReservation(reservationDTO);

        //then
        Mockito.verify(carRepositoryMock).findById(1L);

        //this part of code allows us to assign the argument passed in the last
        // line of our production code
        ArgumentCaptor<ReservationModel> captor = ArgumentCaptor.forClass(ReservationModel.class);
        Mockito.verify(reservationRepositoryMock).save(captor.capture());
        ReservationModel result = captor.getValue();

        //now we can perform assertions on capture argument
        assertThat(result.getEndDate()).isEqualTo("2023-11-22");
        assertThat(result.getStartDate()).isEqualTo("2023-11-20");
        assertThat(result.getCar()).isEqualTo(car);
        assertThat(result.getPrice().intValue()).isEqualTo(200);
    }
}