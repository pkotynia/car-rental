package com.sda.carrental.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<RentModel, Long> {

    @Query("""
            SELECT res.id, res.customer FROM RentModel rentModel
            JOIN rentModel.reservation res
            WHERE res.id = :reservationId""")
        //this list for example could look like [Array[res.id], Array[res.id]]
        //this case is not possible base on our application logic
    List<Object[]> findRentWithReservationId(Long reservationId);
}