package com.tawnydev.springRestMavenPg.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reservation {
    @Id
    private Long id;

    private Integer chambre;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    private Boolean optionPetitDej;

    private Boolean optionSpa;

    private Double coutRestaurant;

    protected Reservation() {
    }
}
