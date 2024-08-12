package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.Customer;
import com.example.product_manager_v2.service.CustomerService;
import com.example.product_manager_v2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerService.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.save(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            Customer customer = customerService.findById(id);
            if (customer != null) {
                customer.setName(customerDetails.getName());
                customer.setEmail(customerDetails.getEmail());
                Customer updatedCustomer = customerService.save(customer);
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            boolean isDeleted = customerService.deleteById(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
