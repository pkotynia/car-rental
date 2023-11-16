package com.sda.carrental.reservation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }
//p to dto, ze duzy json by wyszel z rezerwacjami.rent bedzie juz z zapisana rezerwacja w bazie danych
    @PostMapping
    public RentModel save(@RequestBody @Valid RentDTO rent) {
        return rentService.save(rent);
    }
}
