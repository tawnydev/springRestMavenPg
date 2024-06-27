package com.tawnydev.springRestMavenPg.repositories;

import com.tawnydev.springRestMavenPg.entities.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByName(String name);

    List<Hotel> findAllByName(String name, Pageable pageable);

    Hotel findById(long id);
}
