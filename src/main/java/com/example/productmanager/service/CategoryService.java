package com.example.productmanager.service;

import com.example.productmanager.entity.Category;
import com.example.productmanager.repository.impl.CategoryRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepositoryImpl categoryRepositoryImpl;

    @Autowired
    public CategoryService(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }


    public List<Category> findAll() {
        return categoryRepositoryImpl.findAll();
    }

    public Category readById(Long id) {
        return categoryRepositoryImpl.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found - " + id));
    }
}
