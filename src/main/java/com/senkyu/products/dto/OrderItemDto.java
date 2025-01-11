package com.senkyu.products.dto;

/**
 * DTO for {@link com.senkyu.products.entity.OrderItem}
 */
public record OrderItemDto(Long id, Long orderId, Long productId, CategoryDto productCategory, Integer quantity) {
}