package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.CustomerDao;
import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Customer save(Customer customer) throws DaoException {
        getSession().saveOrUpdate(customer);
        return customer;
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        getSession().delete(getSession().get(Customer.class, id));
    }

    @Transactional
    @Override
    public Customer findById(Long id) throws DaoException {
        return getSession().get(Customer.class, id);
    }

    @Transactional
    @Override
    public List<Customer> findAll() throws DaoException {
        return getSession().createQuery("from Customer", Customer.class).list();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
