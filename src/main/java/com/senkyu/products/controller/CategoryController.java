package com.senkyu.products.controller;

import com.senkyu.products.dto.CategoryDto;
import com.senkyu.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;


    @GetMapping
    public PagedModel<CategoryDto> getAll(Pageable pageable) {
        Page<CategoryDto> categoryDtos = categoryService.getAll(pageable);
        return new PagedModel<>(categoryDtos);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto dto) {
        return categoryService.create(dto);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public CategoryDto delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @GetMapping("/{id}")
    public CategoryDto getOne(@PathVariable Long id) {
        return categoryService.getOne(id);
    }
}
