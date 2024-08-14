package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.CategoryDao;
import com.example.product_manager_v2.entity.Category;
import com.example.product_manager_v2.exception.DaoException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Category save(Category category) throws DaoException {
        try {
            logger.info("Saving category: {}", category);
            getSession().saveOrUpdate(category);
            logger.info("Category saved successfully with ID: {}", category.getId());
            return category;
        } catch (Exception e) {
            logger.error("Failed to save category", e);
            throw new DaoException("Failed to save or update Category", e);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws DaoException {
        try {
            logger.info("Deleting category with ID: {}", id);
            Category category = findById(id);
            if (category != null) {
                getSession().delete(category);
                logger.info("Category deleted successfully with ID: {}", id);
            } else {
                logger.warn("Category with ID: {} not found", id);
                throw new DaoException("Category with ID " + id + " not found");
            }
        } catch (Exception e) {
            logger.error("Failed to delete category with ID: {}", id, e);
            throw new DaoException("Failed to delete Category by ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public Category findById(Long id) throws DaoException {
        try {
            logger.info("Finding category with ID: {}", id);
            Category category = getSession().get(Category.class, id);
            if (category != null) {
                logger.info("Category found with ID: {}", id);
            } else {
                logger.warn("Category with ID: {} not found", id);
            }
            return category;
        } catch (Exception e) {
            logger.error("Failed to find category with ID: {}", id, e);
            throw new DaoException("Failed to find Category by ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public List<Category> findAll() throws DaoException {
        try {
            logger.info("Finding all categories");
            List<Category> categories = getSession().createQuery("from Category", Category.class).list();
            logger.info("Found {} categories", categories.size());
            return categories;
        } catch (Exception e) {
            logger.error("Failed to retrieve all categories", e);
            throw new DaoException("Failed to retrieve all Categories", e);
        }
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
