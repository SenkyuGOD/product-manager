package com.example.product_manager_v2.service;

import com.example.product_manager_v2.entity.Product;

import java.util.List;

public interface ProductService {
     List<Product> findAll() throws  ServiceException;

     Product save(Product product) throws ServiceException;

     Product update(Long id, Product productDetails) throws ServiceException;

     boolean deleteById(Long id) throws ServiceException;

     Product findById(Long id) throws ServiceException;
}
