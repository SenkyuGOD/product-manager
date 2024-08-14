package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.OrderItemDao;
import com.example.product_manager_v2.entity.OrderItem;
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
public class OrderItemDaoImpl implements OrderItemDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderItemDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public OrderItem save(OrderItem orderItem) throws DaoException {
        try {
            logger.info("Saving OrderItem: {}", orderItem);
            getSession().saveOrUpdate(orderItem);
            logger.info("OrderItem saved successfully with ID: {}", orderItem.getId());
            return orderItem;
        } catch (Exception e) {
            logger.error("Failed to save OrderItem", e);
            throw new DaoException("Failed to save or update OrderItem", e);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        try {
            logger.info("Deleting OrderItem with ID: {}", id);
            OrderItem orderItem = findById(id);
            if (orderItem != null) {
                getSession().delete(orderItem);
                logger.info("OrderItem deleted successfully with ID: {}", id);
            } else {
                logger.warn("OrderItem with ID: {} not found", id);
                throw new DaoException("OrderItem with ID " + id + " not found");
            }
        } catch (Exception e) {
            logger.error("Failed to delete OrderItem with ID: {}", id, e);
            throw new DaoException("Failed to delete OrderItem by ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public OrderItem findById(Long id) throws DaoException {
        try {
            logger.info("Finding OrderItem with ID: {}", id);
            OrderItem orderItem = getSession().get(OrderItem.class, id);
            if (orderItem != null) {
                logger.info("OrderItem found with ID: {}", id);
            } else {
                logger.warn("OrderItem with ID: {} not found", id);
            }
            return orderItem;
        } catch (Exception e) {
            logger.error("Failed to find OrderItem with ID: {}", id, e);
            throw new DaoException("Failed to find OrderItem by ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public List<OrderItem> findAll() throws DaoException {
        try {
            logger.info("Finding all OrderItems");
            List<OrderItem> orderItems = getSession().createQuery("from OrderItem", OrderItem.class).list();
            logger.info("Found {} OrderItems", orderItems.size());
            return orderItems;
        } catch (Exception e) {
            logger.error("Failed to retrieve all OrderItems", e);
            throw new DaoException("Failed to retrieve all OrderItems", e);
        }
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
