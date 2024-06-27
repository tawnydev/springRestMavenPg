package com.tawnydev.springRestMavenPg.controllers;

import com.tawnydev.springRestMavenPg.entities.Hotel;
import com.tawnydev.springRestMavenPg.repositories.HotelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {
    private final String ROOT_HOTEL = "/hotels";

    private static Logger logger = LogManager.getLogger(HotelController.class);

    @Autowired
    private HotelRepository HotelRepository;

    @GetMapping(ROOT_HOTEL)
    public List<Hotel> getHotels(Integer page) {
        if (page == null) {
            page = 0;
        }
        List<Hotel> list = new ArrayList<>();
        Pageable pageable = PageRequest.of(page,5, Sort.by("name"));
        for (Hotel hotel : HotelRepository.findAll(pageable)) {
            list.add(hotel);
        }
        logger.info("OK get hotels");
        return list;
    }

    // Single item
    @GetMapping(ROOT_HOTEL + "/{id}")
    Hotel one(@PathVariable Long id) throws Exception {

        return HotelRepository.findById(id)
                .orElseThrow(() -> new Exception("Could not find Hotel " + id));
    }

    @PostMapping(ROOT_HOTEL)
    Hotel newHotel(@RequestBody Hotel newHotel) {
        return HotelRepository.save(newHotel);
    }

    @PutMapping(ROOT_HOTEL + "/{id}")
    Hotel replaceHotel(@RequestBody Hotel newHotel, @PathVariable Long id) {

        return HotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(newHotel.getName());
                    return HotelRepository.save(hotel);
                })
                .orElseGet(() -> {
                    newHotel.setId(id);
                    return HotelRepository.save(newHotel);
                });
    }

    @DeleteMapping(ROOT_HOTEL + "/{id}")
    void deleteHotel(@PathVariable Long id) {
        HotelRepository.deleteById(id);
    }
}
