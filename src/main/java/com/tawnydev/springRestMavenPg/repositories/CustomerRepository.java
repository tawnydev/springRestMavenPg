package com.tawnydev.springRestMavenPg.repositories;

import com.tawnydev.springRestMavenPg.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastname(String lastname);

    List<Customer> findAllByLastname(String lastname, Pageable pageable);

    Customer findById(long id);
}
