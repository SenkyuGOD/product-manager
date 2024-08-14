package com.example.product_manager_v2.dao.impl;

import com.example.product_manager_v2.dao.ProductDao;
import com.example.product_manager_v2.entity.Product;
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
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Product save(Product product) throws DaoException {
        try {
            logger.info("Saving product: {}", product);
            getSession().saveOrUpdate(product);
            logger.info("Product saved successfully with ID: {}", product.getId());
            return product;
        } catch (Exception e) {
            logger.error("Failed to save product: {}", product, e);
            throw new DaoException("Failed to save or update Product", e);
        }
    }

    @Override
    @Transactional
    public List<Product> findAll() throws DaoException {
        try {
            logger.info("Retrieving all products");
            List<Product> products = getSession().createQuery("from Product", Product.class).list();
            logger.info("Found {} products", products.size());
            return products;
        } catch (Exception e) {
            logger.error("Failed to retrieve all products", e);
            throw new DaoException("Failed to retrieve all Products", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws DaoException {
        try {
            logger.info("Deleting product with ID: {}", id);
            Product product = findById(id);
            if (product != null) {
                getSession().delete(product);
                logger.info("Product deleted successfully with ID: {}", id);
            } else {
                logger.warn("Product with ID: {} not found", id);
                throw new DaoException("Product with ID " + id + " not found");
            }
        } catch (Exception e) {
            logger.error("Failed to delete product with ID: {}", id, e);
            throw new DaoException("Failed to delete Product by ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public Product findById(Long id) throws DaoException {
        try {
            logger.info("Finding product with ID: {}", id);
            Product product = getSession().get(Product.class, id);
            if (product != null) {
                logger.info("Product found with ID: {}", id);
            } else {
                logger.warn("Product with ID: {} not found", id);
            }
            return product;
        } catch (Exception e) {
            logger.error("Failed to find product with ID: {}", id, e);
            throw new DaoException("Failed to find Product by ID: " + id, e);
        }
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
