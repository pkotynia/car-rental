package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.BranchesRepository;
import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BranchesRepository branchesRepository;

    public ReservationService(ReservationRepository repository, BranchesRepository branchesRepository) {
        this.reservationRepository = repository;
        this.branchesRepository = branchesRepository;
    }

    ReservationModel saveReservation(ReservationModel reservation) {
        //check if start and end branch exist
        setStartBranchIfExists(reservation);
        setEndBranchIfExists(reservation);
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
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch with name "
                        + endBranchName + " not found"));
    }
}
