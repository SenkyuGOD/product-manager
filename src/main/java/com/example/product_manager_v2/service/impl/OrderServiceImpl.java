package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.OrderDao;
import com.example.product_manager_v2.entity.Order;
import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        logger.info("Fetching all orders");
        try {
            List<Order> orders = orderDao.findAll();
            logger.info("Successfully fetched {} orders", orders.size());
            return orders;
        } catch (DaoException e) {
            logger.error("Error fetching all orders", e);
            throw new ServiceException("Error fetching all orders", e);
        }
    }

    @Override
    public Order save(Order order) throws ServiceException {
        logger.info("Saving order: {}", order);
        try {
            Order savedOrder = orderDao.save(order);
            logger.info("Successfully saved order with ID: {}", savedOrder.getId());
            return savedOrder;
        } catch (DaoException e) {
            logger.error("Error saving order: {}", order, e);
            throw new ServiceException("Error saving order", e);
        }
    }

    @Override
    public Order update(Long id, Order orderDetails) throws ServiceException {
        logger.info("Updating order with ID: {}", id);
        try {
            Order order = orderDao.findById(id);
            if (order == null) {
                logger.warn("Order with ID: {} not found", id);
                throw new ServiceException("Order not found");
            }
            order.setStatus(orderDetails.getStatus());
            order.setCustomer(orderDetails.getCustomer());
            Order updatedOrder = orderDao.save(order);
            logger.info("Successfully updated order with ID: {}", updatedOrder.getId());
            return updatedOrder;
        } catch (DaoException e) {
            logger.error("Error updating order with ID: {}", id, e);
            throw new ServiceException("Error updating order", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        logger.info("Deleting order with ID: {}", id);
        try {
            orderDao.deleteById(id);
            logger.info("Successfully deleted order with ID: {}", id);
            return true;
        } catch (DaoException e) {
            logger.error("Error deleting order with ID: {}", id, e);
            throw new ServiceException("Error deleting order", e);
        }
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        logger.info("Fetching order with ID: {}", id);
        try {
            Order order = orderDao.findById(id);
            if (order == null) {
                logger.warn("Order with ID: {} not found", id);
            } else {
                logger.info("Successfully fetched order with ID: {}", id);
            }
            return order;
        } catch (DaoException e) {
            logger.error("Error fetching order with ID: {}", id, e);
            throw new ServiceException("Error fetching order", e);
        }
    }
}
