package com.senkyu.products.dto;

/**
 * DTO for {@link com.senkyu.products.entity.Product}
 */
public record ProductDto(Long id, String name, String description, double price, CategoryDto category) {
}