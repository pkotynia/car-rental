package com.sda.carrental.reservation;

public class RentAlreadyExistsForReservation extends RuntimeException {

    public RentAlreadyExistsForReservation(String message) {
        super(message);
    }
}
