package com.sda.carrental.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<RentModel, Long> {

    @Query("SELECT res.id FROM RentModel rentModel JOIN rentModel.reservation res WHERE res.id = :reservationId")
    List<Object[]> findRentalswithReservationId(Long reservationId);
}
