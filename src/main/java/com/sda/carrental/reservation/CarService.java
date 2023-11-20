package com.sda.carrental.reservation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    CarModel save(CarModel car) {
        return carRepository.save(car);
    }

    List<CarModel> getAll() {
        return carRepository.findAll();
    }
}

