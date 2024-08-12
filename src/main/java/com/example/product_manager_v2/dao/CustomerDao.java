package com.example.product_manager_v2.dao;

import com.example.product_manager_v2.entity.Customer;
import com.example.product_manager_v2.exception.DaoException;

import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer) throws DaoException;

    void deleteById(Long id) throws DaoException;

    Customer findById(Long id) throws DaoException;

    List<Customer> findAll() throws DaoException;
}
