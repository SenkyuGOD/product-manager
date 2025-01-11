package com.senkyu.products.dto;

import com.senkyu.products.entity.Category;

/**
 * DTO for {@link Category}
 */
public record CategoryDto(Long id, String name, String description) {
}