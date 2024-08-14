package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.OrderItemDao;
import com.example.product_manager_v2.entity.OrderItem;
import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    private final OrderItemDao orderItemDao;

    @Autowired
    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public List<OrderItem> findAll() throws ServiceException {
        logger.info("Fetching all order items");
        try {
            List<OrderItem> orderItems = orderItemDao.findAll();
            logger.info("Successfully fetched {} order items", orderItems.size());
            return orderItems;
        } catch (DaoException e) {
            logger.error("Error fetching all order items", e);
            throw new ServiceException("Error fetching all order items", e);
        }
    }

    @Override
    public OrderItem save(OrderItem orderItem) throws ServiceException {
        logger.info("Saving order item: {}", orderItem);
        try {
            OrderItem savedOrderItem = orderItemDao.save(orderItem);
            logger.info("Successfully saved order item with ID: {}", savedOrderItem.getId());
            return savedOrderItem;
        } catch (DaoException e) {
            logger.error("Error saving order item: {}", orderItem, e);
            throw new ServiceException("Error saving order item", e);
        }
    }

    @Override
    public OrderItem update(Long id, OrderItem orderItemDetails) throws ServiceException {
        logger.info("Updating order item with ID: {}", id);
        try {
            OrderItem orderItem = orderItemDao.findById(id);
            if (orderItem == null) {
                logger.warn("Order item with ID: {} not found", id);
                throw new ServiceException("Order item not found");
            }
            orderItem.setOrder(orderItemDetails.getOrder());
            orderItem.setProduct(orderItemDetails.getProduct());
            orderItem.setQuantity(orderItemDetails.getQuantity());
            OrderItem updatedOrderItem = orderItemDao.save(orderItem);
            logger.info("Successfully updated order item with ID: {}", updatedOrderItem.getId());
            return updatedOrderItem;
        } catch (DaoException e) {
            logger.error("Error updating order item with ID: {}", id, e);
            throw new ServiceException("Error updating order item", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        logger.info("Deleting order item with ID: {}", id);
        try {
            orderItemDao.deleteById(id);
            logger.info("Successfully deleted order item with ID: {}", id);
            return true;
        } catch (DaoException e) {
            logger.error("Error deleting order item with ID: {}", id, e);
            throw new ServiceException("Error deleting order item", e);
        }
    }

    @Override
    public OrderItem findById(Long id) throws ServiceException {
        logger.info("Fetching order item with ID: {}", id);
        try {
            OrderItem orderItem = orderItemDao.findById(id);
            if (orderItem == null) {
                logger.warn("Order item with ID: {} not found", id);
            } else {
                logger.info("Successfully fetched order item with ID: {}", id);
            }
            return orderItem;
        } catch (DaoException e) {
            logger.error("Error fetching order item with ID: {}", id, e);
            throw new ServiceException("Error fetching order item", e);
        }
    }
}
