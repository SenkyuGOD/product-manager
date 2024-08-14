package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.CustomerDao;
import com.example.product_manager_v2.entity.Customer;
import com.example.product_manager_v2.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Customer save(Customer customer) throws DaoException {
        try {
            logger.info("Saving customer: {}", customer);
            getSession().saveOrUpdate(customer);
            logger.info("Customer saved successfully with ID: {}", customer.getId());
            return customer;
        } catch (Exception e) {
            logger.error("Failed to save customer", e);
            throw new DaoException("Failed to save or update Customer", e);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        try {
            logger.info("Deleting customer with ID: {}", id);
            Customer customer = findById(id);
            if (customer != null) {
                getSession().delete(customer);
                logger.info("Customer deleted successfully with ID: {}", id);
            } else {
                logger.warn("Customer with ID: {} not found", id);
                throw new DaoException("Customer with ID " + id + " not found");
            }
        } catch (Exception e) {
            logger.error("Failed to delete customer with ID: {}", id, e);
            throw new DaoException("Failed to delete Customer by ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public Customer findById(Long id) throws DaoException {
        try {
            logger.info("Finding customer with ID: {}", id);
            Customer customer = getSession().get(Customer.class, id);
            if (customer != null) {
                logger.info("Customer found with ID: {}", id);
            } else {
                logger.warn("Customer with ID: {} not found", id);
            }
            return customer;
        } catch (Exception e) {
            logger.error("Failed to find customer with ID: {}", id, e);
            throw new DaoException("Failed to find Customer by ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public List<Customer> findAll() throws DaoException {
        try {
            logger.info("Finding all customers");
            List<Customer> customers = getSession().createQuery("from Customer", Customer.class).list();
            logger.info("Found {} customers", customers.size());
            return customers;
        } catch (Exception e) {
            logger.error("Failed to retrieve all customers", e);
            throw new DaoException("Failed to retrieve all Customers", e);
        }
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
