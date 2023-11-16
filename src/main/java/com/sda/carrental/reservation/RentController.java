package com.sda.carrental.reservation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

//RestController combines Controller and ResponseBody annotations
//Controller - defines endpoints and allowed operations
//Response Body - convert returned object to jsonFormat
@RestController
//defines name of the endpoint
@RequestMapping("/rents")
public class RentController {

    private final RentService service;

    //Dependency injection handled implicitly by Spring
    //since this class is a RestController Spring will try to create been from this class upon startup
    //for crating this been Spring will use bellow constructor
    //Spring will check the parameter and search in Spring Context for a been with matching type
    //If Bean is found it will be passed to the constructor and RentService field will be set
    public RentController(RentService service) {
        this.service = service;
    }

    @PostMapping // defines POST operation for the /rents endpoint
    //RequestBody similar like ResponseBody - allows to change RentDTO object to JSON
    //Valid allow us to check if the passed parameter is valid base on added validation annotations
    //in RentDTO - it will throw MethodArgumentNotValidException that we are handling in
    //GlobalExceptionHandling class
    public RentModel save(@RequestBody @Valid RentDTO rent) {
        return service.save(rent);
    }
}
