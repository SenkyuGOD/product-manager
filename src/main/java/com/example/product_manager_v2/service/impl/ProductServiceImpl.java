package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.dao.ProductDao;
import com.example.product_manager_v2.entity.Product;
import com.example.product_manager_v2.service.ProductService;
import com.example.product_manager_v2.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao dao;

    @Autowired
    public ProductServiceImpl(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> findAll() throws ServiceException {
        try {
            List<Product> products = dao.findAll();
            return products;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product save(Product product) throws ServiceException {
        try {
            return dao.save(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product update(Long id, Product productDetails) throws ServiceException {
        try {
            Product product = dao.findById(id);
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            return dao.save(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Product findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
