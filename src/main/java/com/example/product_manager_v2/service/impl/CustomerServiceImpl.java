package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.CustomerDao;
import com.example.product_manager_v2.entity.Customer;
import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> findAll() throws ServiceException {
        try {
            logger.info("Retrieving all customers");
            List<Customer> customers = customerDao.findAll();
            logger.info("Found {} customers", customers.size());
            return customers;
        } catch (DaoException e) {
            logger.error("Failed to retrieve all customers", e);
            throw new ServiceException("Error retrieving all customers", e);
        }
    }

    @Override
    public Customer save(Customer customer) throws ServiceException {
        try {
            logger.info("Saving customer: {}", customer);
            Customer savedCustomer = customerDao.save(customer);
            logger.info("Customer saved successfully with ID: {}", savedCustomer.getId());
            return savedCustomer;
        } catch (DaoException e) {
            logger.error("Failed to save customer: {}", customer, e);
            throw new ServiceException("Error saving customer", e);
        }
    }

    @Override
    public Customer update(Long id, Customer customerDetails) throws ServiceException {
        try {
            logger.info("Updating customer with ID: {}", id);
            Customer customer = customerDao.findById(id);
            if (customer != null) {
                customer.setName(customerDetails.getName());
                customer.setEmail(customerDetails.getEmail());
                Customer updatedCustomer = customerDao.save(customer);
                logger.info("Customer updated successfully with ID: {}", id);
                return updatedCustomer;
            } else {
                logger.warn("Customer with ID: {} not found for update", id);
                throw new ServiceException("Customer not found");
            }
        } catch (DaoException e) {
            logger.error("Failed to update customer with ID: {}", id, e);
            throw new ServiceException("Error updating customer", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            logger.info("Deleting customer with ID: {}", id);
            customerDao.deleteById(id);
            logger.info("Customer deleted successfully with ID: {}", id);
            return true;
        } catch (DaoException e) {
            logger.error("Failed to delete customer with ID: {}", id, e);
            throw new ServiceException("Error deleting customer", e);
        }
    }

    @Override
    public Customer findById(Long id) throws ServiceException {
        try {
            logger.info("Finding customer with ID: {}", id);
            Customer customer = customerDao.findById(id);
            if (customer != null) {
                logger.info("Customer found with ID: {}", id);
            } else {
                logger.warn("Customer with ID: {} not found", id);
            }
            return customer;
        } catch (DaoException e) {
            logger.error("Failed to find customer with ID: {}", id, e);
            throw new ServiceException("Error finding customer", e);
        }
    }
}
