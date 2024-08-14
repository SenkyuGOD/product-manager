package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.Category;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            logger.info("Request received to retrieve all categories");
            List<Category> categories = categoryService.findAll();
            logger.info("Successfully retrieved all categories, count: {}", categories.size());
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occurred while retrieving all categories", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            logger.info("Request received to create a new category: {}", category);
            Category createdCategory = categoryService.save(category);
            logger.info("Successfully created category with ID: {}", createdCategory.getId());
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } catch (ServiceException e) {
            logger.error("Error occurred while creating a new category", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        try {
            logger.info("Request received to update category with ID: {}", id);
            Category updatedCategory = categoryService.update(id, categoryDetails);
            if (updatedCategory != null) {
                logger.info("Successfully updated category with ID: {}", updatedCategory.getId());
                return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
            } else {
                logger.warn("Category with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while updating category with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            logger.info("Request received to delete category with ID: {}", id);
            boolean isDeleted = categoryService.deleteById(id);
            if (isDeleted) {
                logger.info("Successfully deleted category with ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Category with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while deleting category with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
