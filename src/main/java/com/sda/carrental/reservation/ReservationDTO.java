package com.sda.carrental.reservation;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDTO(
        @NotNull String customer,
        @NotNull Long carId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull Long startBranchId,
        @NotNull Long endBranchId
) {
}
