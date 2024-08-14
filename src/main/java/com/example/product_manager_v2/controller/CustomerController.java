package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.Customer;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            logger.info("Request received to retrieve all customers");
            List<Customer> customers = customerService.findAll();
            logger.info("Successfully retrieved all customers, count: {}", customers.size());
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occurred while retrieving all customers", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            logger.info("Request received to create a new customer: {}", customer);
            Customer createdCustomer = customerService.save(customer);
            logger.info("Successfully created customer with ID: {}", createdCustomer.getId());
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (ServiceException e) {
            logger.error("Error occurred while creating a new customer", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            logger.info("Request received to update customer with ID: {}", id);
            Customer customer = customerService.findById(id);
            if (customer != null) {
                customer.setName(customerDetails.getName());
                customer.setEmail(customerDetails.getEmail());
                Customer updatedCustomer = customerService.save(customer);
                logger.info("Successfully updated customer with ID: {}", updatedCustomer.getId());
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } else {
                logger.warn("Customer with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while updating customer with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            logger.info("Request received to delete customer with ID: {}", id);
            boolean isDeleted = customerService.deleteById(id);
            if (isDeleted) {
                logger.info("Successfully deleted customer with ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Customer with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while deleting customer with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
