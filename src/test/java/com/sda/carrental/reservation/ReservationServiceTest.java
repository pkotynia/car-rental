package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.BranchesRepository;
import com.sda.carrental.car_rental_facility.BranchesModel;
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

//this is a unit test. We need to mock all dependencies in order to test only the code from ReservationService class

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

    //This annotation will create ReservationService object and inject Mock created above via constructor
    @InjectMocks
    private ReservationService reservationService;

    @Test
    void shouldSaveReservation() {

        //given
        //this object will be passed as an argument to saveReservation method
        ReservationDTO reservationDto = new ReservationDTO(
                "Ted",
                1L,
                LocalDate.of(2023,11,20),
                LocalDate.of(2023,11,22),
                1L,
                2L
        );

        //here we are recording behavior of branchesRepositoryMock
        //when findById will be invoked in the method code we will return startBranch object
        BranchesModel startBranch = new BranchesModel(1L, "Warszawa");
        Mockito.when(branchesRepositoryMock.findById(1L)).thenReturn(Optional.of(startBranch));

        BranchesModel endBranch = new BranchesModel(2L, "Gdynia");
        Mockito.when(branchesRepositoryMock.findById(2L)).thenReturn(Optional.of(endBranch));

        //here we are recording behavior of carRepositoryMock mock.
        //Whenever in production code .findById() with 1L as an argument method will be called car object will be returned
        CarModel car = new CarModel(
                1L,
                "KIA",
                "Ceed",
                "Sedan",
                CarStatus.AVAILABLE,
                BigDecimal.valueOf(100)
        );

        Mockito.when(carRepositoryMock.findById(1L)).thenReturn(Optional.of(car));

        //when
        //here we are calling method on our Object Under Test
        //We will not be able to do any assertions on returned value because in our prod code last operation is:
        //return reservationRepository.save(reservation); - and we have mocked reservationRepository
        //We are checking if our code works properly verifying the argument passed to reservationRepository Mock
        reservationService.saveReservation(reservationDto);

        //then
        Mockito.verify(carRepositoryMock).findById(1L);

        //This part of code allows us to assign argument passed in the last line of our production code to result variable
        ArgumentCaptor<ReservationModel> captor = ArgumentCaptor.forClass(ReservationModel.class);
        Mockito.verify(reservationRepositoryMock).save(captor.capture());
        ReservationModel result = captor.getValue();

        //now we can perform assertions on captured argument
        assertThat(result.getEndDate()).isEqualTo("2023-11-22");
        assertThat(result.getStartDate()).isEqualTo("2023-11-20");
        assertThat(result.getPrice().intValue()).isEqualTo(200);
        assertThat(result.getCar()).isEqualTo(car);
    }
}