package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.dao.ProductDao;
import com.example.product_manager_v2.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Product save(Product product) throws DaoException {
        getSession().saveOrUpdate(product);
        return product;
    }

    @Transactional
    @Override
    public List<Product> findAll() throws DaoException {
        return getSession().createQuery("from Product", Product.class).list();
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        getSession().delete(getSession().get(Product.class, id));
    }

    @Transactional
    @Override
    public Product findById(Long id) throws DaoException {
        return getSession().get(Product.class, id);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
