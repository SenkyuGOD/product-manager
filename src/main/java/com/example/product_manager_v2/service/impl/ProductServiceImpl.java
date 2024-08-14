package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.ProductDao;
import com.example.product_manager_v2.entity.Product;
import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDao dao;

    @Autowired
    public ProductServiceImpl(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> findAll() throws ServiceException {
        logger.info("Fetching all products");
        try {
            List<Product> products = dao.findAll();
            logger.info("Successfully fetched {} products", products.size());
            return products;
        } catch (DaoException e) {
            logger.error("Error fetching all products", e);
            throw new ServiceException("Error fetching all products", e);
        }
    }

    @Override
    public Product save(Product product) throws ServiceException {
        logger.info("Saving product: {}", product);
        try {
            Product savedProduct = dao.save(product);
            logger.info("Successfully saved product with ID: {}", savedProduct.getId());
            return savedProduct;
        } catch (DaoException e) {
            logger.error("Error saving product: {}", product, e);
            throw new ServiceException("Error saving product", e);
        }
    }

    @Override
    public Product update(Long id, Product productDetails) throws ServiceException {
        logger.info("Updating product with ID: {}", id);
        try {
            Product product = dao.findById(id);
            if (product == null) {
                logger.warn("Product with ID: {} not found", id);
                throw new ServiceException("Product not found");
            }
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            Product updatedProduct = dao.save(product);
            logger.info("Successfully updated product with ID: {}", updatedProduct.getId());
            return updatedProduct;
        } catch (DaoException e) {
            logger.error("Error updating product with ID: {}", id, e);
            throw new ServiceException("Error updating product", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        logger.info("Deleting product with ID: {}", id);
        try {
            dao.deleteById(id);
            logger.info("Successfully deleted product with ID: {}", id);
            return true;
        } catch (DaoException e) {
            logger.error("Error deleting product with ID: {}", id, e);
            throw new ServiceException("Error deleting product", e);
        }
    }

    @Override
    public Product findById(Long id) throws ServiceException {
        logger.info("Fetching product with ID: {}", id);
        try {
            Product product = dao.findById(id);
            if (product == null) {
                logger.warn("Product with ID: {} not found", id);
            } else {
                logger.info("Successfully fetched product with ID: {}", id);
            }
            return product;
        } catch (DaoException e) {
            logger.error("Error fetching product with ID: {}", id, e);
            throw new ServiceException("Error fetching product", e);
        }
    }
}
