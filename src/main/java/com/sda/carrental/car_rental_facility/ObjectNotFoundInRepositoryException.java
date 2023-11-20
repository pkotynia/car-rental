package com.sda.carrental.car_rental_facility;

public class ObjectNotFoundInRepositoryException extends RuntimeException {

    public ObjectNotFoundInRepositoryException(String message) {
        super(message);
    }
}