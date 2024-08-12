package com.example.product_manager_v2.dao;

import com.example.product_manager_v2.entity.Category;
import com.example.product_manager_v2.exception.DaoException;

import java.util.List;

public interface CategoryDao {
    Category save(Category category) throws DaoException;

    void deleteById(Long id) throws DaoException;

    Category findById(Long id) throws DaoException;

    List<Category> findAll() throws DaoException;
}
