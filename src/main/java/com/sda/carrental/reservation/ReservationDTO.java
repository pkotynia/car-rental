package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDTO(
        @NotNull String customer,
        @NotNull Long carId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull CompanyBranchModel startBranch,
        @NotNull CompanyBranchModel endBranch
) {
}
