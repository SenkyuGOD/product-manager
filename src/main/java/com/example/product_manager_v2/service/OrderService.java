package com.example.product_manager_v2.service;

import com.example.product_manager_v2.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll() throws ServiceException;

    Order findById(Long id) throws ServiceException;

    Order save(Order order) throws ServiceException;

    Order update(Long id, Order orderDetails) throws ServiceException;

    boolean deleteById(Long id) throws ServiceException;
}
