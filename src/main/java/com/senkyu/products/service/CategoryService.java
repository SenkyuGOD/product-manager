package com.senkyu.products.service;

import com.senkyu.products.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> getAll(Pageable pageable);

    CategoryDto create(CategoryDto dto);

    CategoryDto update(Long id, CategoryDto dto);

    CategoryDto delete(Long id);

    CategoryDto getOne(Long id);
}
