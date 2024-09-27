package com.tawnydev.springRestMavenPg.controllers;

import com.tawnydev.springRestMavenPg.entities.Reservation;
import com.tawnydev.springRestMavenPg.repositories.ReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {
    private final String ROOT_RESERVATION = "/reservations";

    private static Logger logger = LogManager.getLogger(ReservationController.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping(ROOT_RESERVATION)
    public List<Reservation> getReservations(Integer page) {
        if (page == null) {
            page = 0;
        }
        List<Reservation> list = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, 5, Sort.by("hotel"));
        for (Reservation res : reservationRepository.findAll(pageable)) {
            list.add(res);
        }
        logger.info("OK get hotels");
        List<Reservation> filteredList = list.stream().filter((res) -> res.getOptionSpa()).collect(Collectors.toList());
        Double resTot = list.stream().map(res -> res.getCoutRestaurant()).reduce(0.0, Double::sum);
        logger.info("somme des cout restaurant = " + resTot);
        return filteredList;
    }

    // Single item
    @GetMapping(ROOT_RESERVATION + "/{id}")
    Reservation one(@PathVariable Long id) throws Exception {

        return reservationRepository.findById(id)
                .orElseThrow(() -> new Exception("Could not find Reservation " + id));
    }

    @PostMapping(ROOT_RESERVATION)
    Reservation newReservation(@RequestBody Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

    @PutMapping(ROOT_RESERVATION + "/{id}")
    Reservation replaceReservation(@RequestBody Reservation newReservation, @PathVariable Long id) {

        return reservationRepository.findById(id)
                .map(res -> {
                    if (newReservation.getChambre() != null) {
                        res.setChambre(newReservation.getChambre());
                    }
                    if (newReservation.getCustomer() != null) {
                        res.setCustomer(newReservation.getCustomer());
                    }
                    if (newReservation.getHotel() != null) {
                        res.setHotel(newReservation.getHotel());
                    }
                    if (newReservation.getOptionPetitDej() != null) {
                        res.setOptionPetitDej(newReservation.getOptionPetitDej());
                    }
                    if (newReservation.getOptionSpa() != null) {
                        res.setOptionSpa(newReservation.getOptionSpa());
                    }
                    if (newReservation.getCoutRestaurant() != null) {
                        res.setCoutRestaurant(newReservation.getCoutRestaurant());
                    }
                    return reservationRepository.save(res);
                })
                .orElseGet(() -> {
                    newReservation.setId(id);
                    return reservationRepository.save(newReservation);
                });
    }

    @DeleteMapping(ROOT_RESERVATION + "/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }
}
