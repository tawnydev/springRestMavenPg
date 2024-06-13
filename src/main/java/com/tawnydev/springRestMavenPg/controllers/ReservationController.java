package com.tawnydev.springRestMavenPg.controllers;

import com.tawnydev.springRestMavenPg.entities.Reservation;
import com.tawnydev.springRestMavenPg.repositories.ReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {
    private final String ROOT_RESERVATION = "/reservations";

    private static Logger logger = LogManager.getLogger(ReservationController.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping(ROOT_RESERVATION)
    public List<Reservation> getReservations() {
        List<Reservation> list = new ArrayList<>();
        for (Reservation res : reservationRepository.findAll()) {
            list.add(res);
        }
        logger.info("OK get hotels");
        return list;
    }
//
//    // Single item
//    @GetMapping(ROOT_RESERVATION + "/{id}")
//    Reservation one(@PathVariable Long id) throws Exception {
//
//        return reservationRepository.findById(id)
//                .orElseThrow(() -> new Exception("Could not find Reservation " + id));
//    }

    @PostMapping(ROOT_RESERVATION)
    Reservation newReservation(@RequestBody Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

//    @PutMapping(ROOT_RESERVATION + "/{id}")
//    Reservation replaceReservation(@RequestBody Reservation newReservation, @PathVariable Long id) {
//
//        return reservationRepository.findById(id)
//                .map(hotel -> {
//                    hotel.setName(newReservation.getName());
//                    return reservationRepository.save(hotel);
//                })
//                .orElseGet(() -> {
//                    newReservation.setId(id);
//                    return reservationRepository.save(newReservation);
//                });
//    }

//    @DeleteMapping(ROOT_RESERVATION + "/{id}")
//    void deleteReservation(@PathVariable Long id) {
//        reservationRepository.deleteById(id);
//    }
}
