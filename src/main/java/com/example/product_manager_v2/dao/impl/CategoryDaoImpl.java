package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.CategoryDao;
import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Category save(Category category) throws DaoException {
        getSession().saveOrUpdate(category);
        return category;
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        getSession().delete(getSession().get(Category.class, id));
    }


    @Transactional
    @Override
    public Category findById(Long id) throws DaoException {
        return getSession().get(Category.class, id);
    }

    @Transactional
    @Override
    public List<Category> findAll() throws DaoException {
        return getSession().createQuery("from Category", Category.class).list();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
