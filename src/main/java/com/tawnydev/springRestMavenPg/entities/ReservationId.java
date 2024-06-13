package com.tawnydev.springRestMavenPg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReservationId implements Serializable {
    private Integer chambre;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    // default constructor

    protected ReservationId() {}

    public ReservationId(Integer chambre, Hotel hotel,Customer customer) {
        this.chambre = chambre;
        this.hotel = hotel;
        this.customer = customer;
    }
}
