package com.example.product_manager_v2.service;

import com.example.product_manager_v2.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll() throws ServiceException;

    Customer findById(Long id) throws ServiceException;

    Customer save(Customer customer) throws ServiceException;

    Customer update(Long id, Customer customerDetails) throws ServiceException;

    boolean deleteById(Long id) throws ServiceException;
}
