package com.tawnydev.springRestMavenPg.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    protected Hotel() {}

    public Hotel(String name){
        this.name = name;
    }
}
