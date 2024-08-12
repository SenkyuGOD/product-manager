package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.dao.OrderItemDao;
import com.example.product_manager_v2.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public OrderItem save(OrderItem orderItem) throws DaoException {
        getSession().saveOrUpdate(orderItem);
        return orderItem;
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        getSession().delete(getSession().get(OrderItem.class, id));
    }

    @Transactional
    @Override
    public OrderItem findById(Long id) throws DaoException {
        return getSession().get(OrderItem.class, id);
    }

    @Transactional
    @Override
    public List<OrderItem> findAll() throws DaoException {
        return getSession().createQuery("from OrderItem", OrderItem.class).list();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
