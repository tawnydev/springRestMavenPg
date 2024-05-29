package com.tawnydev.springRestMavenPg.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        @Column(length = 20)
        private String firstname;
        @Column(length = 20)
        private String lastname;

        protected Customer() {}

        public Customer(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

}
