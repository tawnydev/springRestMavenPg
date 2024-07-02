package com.tawnydev.springRestMavenPg.repositories;

import com.tawnydev.springRestMavenPg.entities.Hotel;
import com.tawnydev.springRestMavenPg.entities.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByChambre(Integer chambre, Pageable pageable);
    List<Reservation> findAllByHotel(Hotel hotel, Pageable pageable);

}
