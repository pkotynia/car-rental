package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.BranchesRepository;
import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.math.BigDecimal;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BranchesRepository branchesRepository;
    private final CarRepository carRepository;

    public ReservationService(ReservationRepository repository, BranchesRepository branchesRepository, CarRepository carRepository) {
        this.reservationRepository = repository;
        this.branchesRepository = branchesRepository;
        this.carRepository = carRepository;
    }

    ReservationModel saveReservation(ReservationDTO reservationDTO) {
        ReservationModel reservation = new ReservationModel();

        reservation.setEndBranch(reservationDTO.endBranch());
        reservation.setStartBranch(reservationDTO.startBranch());
        setStartBranchIfExists(reservation);
        setEndBranchIfExists(reservation);

        reservation.setCustomer(reservationDTO.customer());
        reservation.setStartDate(reservationDTO.startDate());
        reservation.setEndDate(reservationDTO.endDate());
        //todo fixme
        reservation.setPrice(new BigDecimal(100));

        CarModel carFromRepo = carRepository.findById(reservationDTO.carId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("car not found"));

        reservation.setCar(carFromRepo);

        return reservationRepository.save(reservation);
    }

    private void setEndBranchIfExists(ReservationModel reservation) {
        String endBranchName = reservation.getEndBranch().getName();
        CompanyBranchModel endBranch = getCompanyBranchModel(endBranchName);
        reservation.setEndBranch(endBranch);
    }

    private void setStartBranchIfExists(ReservationModel reservation) {
        String startBranchName = reservation.getStartBranch().getName();
        CompanyBranchModel startBranch = getCompanyBranchModel(startBranchName);
        reservation.setStartBranch(startBranch);
    }

    private CompanyBranchModel getCompanyBranchModel(String endBranchName) {
        return branchesRepository.findByName(endBranchName)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch with name " + endBranchName + " not found"));
    }
}