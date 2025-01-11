package com.senkyu.products.service.impl;

import com.senkyu.products.dto.CategoryDto;
import com.senkyu.products.entity.Category;
import com.senkyu.products.mapper.CategoryMapper;
import com.senkyu.products.repository.CategoryRepository;
import com.senkyu.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDto> getAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toCategoryDto);
    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category category = categoryMapper.toEntity(dto);
        Category resultCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(resultCategory);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
        categoryMapper.updateWithNull(dto, category);
        Category resultCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(resultCategory);
    }

    @Override
    public CategoryDto delete(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
        }
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto getOne(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryMapper.toCategoryDto(categoryOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }
}
