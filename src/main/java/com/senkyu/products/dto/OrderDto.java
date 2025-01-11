package com.senkyu.products.dto;

import java.util.List;

/**
 * DTO for {@link com.senkyu.products.entity.Order}
 */
public record OrderDto(Long id, String status, Long customerId, List<OrderItemDto> orderItems) {
}