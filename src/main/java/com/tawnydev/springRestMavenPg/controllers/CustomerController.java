package com.tawnydev.springRestMavenPg.controllers;

import com.tawnydev.springRestMavenPg.entities.Customer;
import com.tawnydev.springRestMavenPg.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class CustomerController {
    private final String ROOT_CUSTOMER= "/customers";

    private static Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(ROOT_CUSTOMER)
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> list = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            list.add(customer);
            logger.info(customer.getId());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
