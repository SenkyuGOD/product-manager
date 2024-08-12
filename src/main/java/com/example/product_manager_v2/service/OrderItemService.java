package com.example.product_manager_v2.service;

import com.example.product_manager_v2.entity.OrderItem;
import com.example.product_manager_v2.exception.ServiceException;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll() throws ServiceException;

    OrderItem findById(Long id) throws ServiceException;

    OrderItem save(OrderItem orderItem) throws ServiceException;

    OrderItem update(Long id, OrderItem orderItemDetails) throws ServiceException;

    boolean deleteById(Long id) throws ServiceException;
}
