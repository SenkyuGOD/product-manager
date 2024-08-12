package com.example.product_manager_v2.service;

import com.example.product_manager_v2.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll() throws ServiceException;

    Category findById(Long id) throws ServiceException;

    Category save(Category category) throws ServiceException;

    Category update(Long id, Category categoryDetails) throws ServiceException;

    boolean deleteById(Long id) throws ServiceException;


}
