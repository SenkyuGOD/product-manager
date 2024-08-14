package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.OrderDao;
import com.example.product_manager_v2.entity.Order;
import com.example.product_manager_v2.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Order save(Order order) throws DaoException {
        try {
            getSession().saveOrUpdate(order);
            return order;
        } catch (Exception e) {
            throw new DaoException("Failed to save or update Order", e);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        try {
            Order order = findById(id);
            if (order != null) {
                getSession().delete(order);
            } else {
                throw new DaoException("Order with id " + id + " not found");
            }
        } catch (Exception e) {
            throw new DaoException("Failed to delete Order by id " + id, e);
        }
    }

    @Transactional
    @Override
    public Order findById(Long id) throws DaoException {
        try {
            Order order = getSession().get(Order.class, id);
            if (order == null) {
                throw new DaoException("Order with id " + id + " not found");
            }
            return order;
        } catch (Exception e) {
            throw new DaoException("Failed to find Order by id " + id, e);
        }
    }

    @Transactional
    @Override
    public List<Order> findAll() throws DaoException {
        try {
            return getSession().createQuery("from Order", Order.class).list();
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve all Orders", e);
        }
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

