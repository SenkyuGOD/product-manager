package com.example.product_manager_v2.dao;

import com.example.product_manager_v2.entity.Order;
import com.example.product_manager_v2.exception.DaoException;

import java.util.List;

public interface OrderDao {
    Order save(Order order) throws DaoException;

    void deleteById(Long id) throws DaoException;

    Order findById(Long id) throws DaoException;

    List<Order> findAll() throws DaoException;
}
