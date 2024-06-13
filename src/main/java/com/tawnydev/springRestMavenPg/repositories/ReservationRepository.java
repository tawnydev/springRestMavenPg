package com.tawnydev.springRestMavenPg.repositories;

import com.tawnydev.springRestMavenPg.entities.Reservation;
import com.tawnydev.springRestMavenPg.entities.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
//    List<Reservation> findByHotel(Long hotelId);
//    List<Reservation> findByCustomer(Long customerId);
//    List<Reservation> findByHotelAndCustomer(Long hotelId, Long customerId);

}
