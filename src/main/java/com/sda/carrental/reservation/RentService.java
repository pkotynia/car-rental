package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;

    private final ReservationRepository reservationRepository;

    public RentService(RentRepository rentRepository, ReservationRepository reservationRepository) {
        this.rentRepository = rentRepository;
        this.reservationRepository = reservationRepository;
    }

    RentModel save(RentDTO rentModel) {
        //check if rent with given reservation id already exist in database
        List<Object[]> reservationIds = rentRepository.findRentalsWithReservationId(rentModel.reservationId());

        if (!reservationIds.isEmpty()) {
           throw new RentAlreadyExistsForReservation("Rent already exist for reservation with id " + rentModel.reservationId());
        }

        //if exists we need to throw exception. It is not possible to have more than one rent for the same reservation

        RentModel rentToSave = new RentModel();
        rentToSave.setEmployee(rentModel.employee());
        rentToSave.setRentDate(rentModel.rentDate());
        rentToSave.setComments(rentModel.comments());

        ReservationModel reservationFromRepository = reservationRepository.findById(rentModel.reservationId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Reservation with id " +
                        rentModel.reservationId() +
                        " not found"));

        rentToSave.setReservation(reservationFromRepository);

        return rentRepository.save(rentToSave);
    }
}
