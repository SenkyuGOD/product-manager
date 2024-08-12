package com.example.product_manager_v2.dao;

import com.example.product_manager_v2.entity.Product;

import java.util.List;

public interface ProductDao {
    Product save(Product product) throws DaoException;

    List<Product> findAll() throws DaoException;

    void deleteById(Long id) throws DaoException;

    Product findById(Long id) throws DaoException;


}
