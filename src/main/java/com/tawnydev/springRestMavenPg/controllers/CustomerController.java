package com.tawnydev.springRestMavenPg.controllers;

import com.tawnydev.springRestMavenPg.entities.Customer;
import com.tawnydev.springRestMavenPg.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private final String ROOT_CUSTOMER = "/customers";

    private static Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(ROOT_CUSTOMER)
    public List<Customer> getCustomers(Integer page) {
        if (page == null) {
            page = 0;
        }
        List<Customer> list = new ArrayList<>();
        Pageable pageWithFiveElements = PageRequest.of(page, 5, Sort.by("lastname"));
        for (Customer customer : customerRepository.findAll(pageWithFiveElements)) {
            list.add(customer);
        }
        logger.info("OK get customers");
        return list;
    }

    // Single item
    @GetMapping(ROOT_CUSTOMER + "/{id}")
    Customer one(@PathVariable Long id) throws Exception {
        return customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Could not find customer " + id));
    }

    @PostMapping(ROOT_CUSTOMER)
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PutMapping(ROOT_CUSTOMER + "/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstname(newCustomer.getFirstname());
                    customer.setLastname(newCustomer.getLastname());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    @DeleteMapping(ROOT_CUSTOMER + "/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
