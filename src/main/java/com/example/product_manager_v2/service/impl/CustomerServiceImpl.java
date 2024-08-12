package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.CustomerDao;
import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.entity.Customer;
import com.example.product_manager_v2.service.CustomerService;
import com.example.product_manager_v2.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> findAll() throws ServiceException {
        try {
            return customerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer save(Customer customer) throws ServiceException {
        try {
            return customerDao.save(customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer update(Long id, Customer customerDetails) throws ServiceException {
        try {
            Customer customer = customerDao.findById(id);
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            return customerDao.save(customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            customerDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }


    @Override
    public Customer findById(Long id) throws ServiceException {
        try {
            return customerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
