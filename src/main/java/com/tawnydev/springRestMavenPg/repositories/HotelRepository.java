package com.tawnydev.springRestMavenPg.repositories;

import com.tawnydev.springRestMavenPg.entities.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findByName(String name);

    Hotel findById(long id);
}
