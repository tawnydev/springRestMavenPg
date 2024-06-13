package com.tawnydev.springRestMavenPg.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(ReservationId.class)
public class Reservation {
    @Id
    private Integer chambre;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    private Boolean optionPetitDej;

    private Boolean optionSpa;

    private Double coutRestaurant;

    protected Reservation() {
    }
}
