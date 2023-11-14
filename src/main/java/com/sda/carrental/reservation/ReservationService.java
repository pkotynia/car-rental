package com.sda.carrental.reservation;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    ReservationModel saveReservation(ReservationModel reservation) {
        return repository.save(reservation);
    }
}
