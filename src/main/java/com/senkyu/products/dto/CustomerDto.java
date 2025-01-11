package com.senkyu.products.dto;

/**
 * DTO for {@link com.senkyu.products.entity.Customer}
 */
public record CustomerDto(Long id, String name, String email) {
}