package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.CategoryDao;
import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.entity.Category;
import com.example.product_manager_v2.service.CategoryService;
import com.example.product_manager_v2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao dao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.dao = categoryDao;
    }

    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Category save(Category category) throws ServiceException {
        try {
            return dao.save(category);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Category update(Long id, Category categoryDetails) throws ServiceException {
        try {
            Category category = dao.findById(id);
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            return dao.save(category);
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
    public Category findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
