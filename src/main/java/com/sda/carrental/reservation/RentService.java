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

    RentModel save(RentDTO rentDTO) throws RentAlreadyExistsForReservation {
        //check if rent with given reservation is already exists in database
        throwExceptionIfRentWithReservationIdAlreadyExist(rentDTO.reservationId());
        RentModel rentToSave = createRentModelObjectBaseOnRentDTO(rentDTO);
        return rentRepository.save(rentToSave);
    }

    private RentModel createRentModelObjectBaseOnRentDTO(RentDTO rentModel) {
        RentModel rentToSave = new RentModel();
        rentToSave.setEmployee(rentModel.employee());
        rentToSave.setRentDate(rentModel.rentDate());
        rentToSave.setComments(rentModel.comments());

        ReservationModel reservationFromRepository = findReservationById(rentModel.reservationId());
        rentToSave.setReservation(reservationFromRepository);
        return rentToSave;
    }

    //if exists we need to throw exception. it's not possible to have more than one rent
    // for the same reservation
    private ReservationModel findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Reservation with id " +
                        reservationId + " not found"));
    }

    private void throwExceptionIfRentWithReservationIdAlreadyExist(Long reservationId) {
        //we are using custom method added to repository
        //which returns List of one element arrays [reservationID]
        List<Object[]> reservationIds = rentRepository.findRentWithReservationId(reservationId);
        //we're checking if query is empty to verify that there are no rents for reservation yet
        if (!reservationIds.isEmpty()) {
            throw new RentAlreadyExistsForReservation("Rent already exists for reservation with id "
                    + reservationId);
        }
    }
}
