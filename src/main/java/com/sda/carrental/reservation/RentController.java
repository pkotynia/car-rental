package com.sda.carrental.reservation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;

    //Dependency injection handled implicitly by Spring
    //since this class is a RestController, Spring'll try to create a bean from this class upon start
    //for creating this been, Spring'll use below constructor
    //Spring will check the parameter and search in Spring Contex for a bean with matching type
    //if bean is found, it will be passed to the constructor and RentService field will be set
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

//1.Po to dto, ze duzy json by wyszedl z rezerwacjami.rent bedzie juz z zapisana rezerwacja w bazie danych.
    //2.defines POST operation for the /rents endpoint.
    //3.RequestBody jest analogiczne do ResponseBody - allows to change RentDTO object to Json.
    //4.Valid allow us to check if the passed parameter is valid base on added validation
   //annotations in RentDTO - it will throw MethodArgumentNotValidException,
   // that we are handling in GlobalExceptionHandling.
    @PostMapping
    public RentModel save(@RequestBody @Valid RentDTO rent) {
        return rentService.save(rent);
    }
}
