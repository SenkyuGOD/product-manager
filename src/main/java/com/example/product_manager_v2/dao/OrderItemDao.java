package com.example.product_manager_v2.dao;

import com.example.product_manager_v2.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    OrderItem save(OrderItem orderItem) throws DaoException;

    OrderItem findById(Long id) throws DaoException;

    List<OrderItem> findAll() throws DaoException;

    void deleteById(Long id) throws DaoException;

}
