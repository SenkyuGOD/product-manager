package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.CategoryDao;
import com.example.product_manager_v2.entity.Category;
import com.example.product_manager_v2.exception.DaoException;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryDao dao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.dao = categoryDao;
    }

    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            logger.info("Retrieving all categories");
            List<Category> categories = dao.findAll();
            logger.info("Found {} categories", categories.size());
            return categories;
        } catch (DaoException e) {
            logger.error("Failed to retrieve all categories", e);
            throw new ServiceException("Error retrieving all categories", e);
        }
    }

    @Override
    public Category save(Category category) throws ServiceException {
        try {
            logger.info("Saving category: {}", category);
            Category savedCategory = dao.save(category);
            logger.info("Category saved successfully with ID: {}", savedCategory.getId());
            return savedCategory;
        } catch (DaoException e) {
            logger.error("Failed to save category: {}", category, e);
            throw new ServiceException("Error saving category", e);
        }
    }

    @Override
    public Category update(Long id, Category categoryDetails) throws ServiceException {
        try {
            logger.info("Updating category with ID: {}", id);
            Category category = dao.findById(id);
            if (category != null) {
                category.setName(categoryDetails.getName());
                category.setDescription(categoryDetails.getDescription());
                Category updatedCategory = dao.save(category);
                logger.info("Category updated successfully with ID: {}", id);
                return updatedCategory;
            } else {
                logger.warn("Category with ID: {} not found for update", id);
                throw new ServiceException("Category not found");
            }
        } catch (DaoException e) {
            logger.error("Failed to update category with ID: {}", id, e);
            throw new ServiceException("Error updating category", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            logger.info("Deleting category with ID: {}", id);
            dao.deleteById(id);
            logger.info("Category deleted successfully with ID: {}", id);
            return true;
        } catch (DaoException e) {
            logger.error("Failed to delete category with ID: {}", id, e);
            throw new ServiceException("Error deleting category", e);
        }
    }

    @Override
    public Category findById(Long id) throws ServiceException {
        try {
            logger.info("Finding category with ID: {}", id);
            Category category = dao.findById(id);
            if (category != null) {
                logger.info("Category found with ID: {}", id);
            } else {
                logger.warn("Category with ID: {} not found", id);
            }
            return category;
        } catch (DaoException e) {
            logger.error("Failed to find category with ID: {}", id, e);
            throw new ServiceException("Error finding category", e);
        }
    }
}
