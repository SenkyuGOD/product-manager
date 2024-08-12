package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.dao.OrderDao;
import com.example.product_manager_v2.entity.Order;
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
        getSession().saveOrUpdate(order);
        return order;
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        getSession().delete(getSession().get(Order.class, id));
    }

    @Transactional
    @Override
    public Order findById(Long id) throws DaoException {
        return getSession().get(Order.class, id);
    }

    @Transactional
    @Override
    public List<Order> findAll() throws DaoException {
        return getSession().createQuery("from Order", Order.class).list();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
