package com.senkyu.products.service;

import com.senkyu.products.dto.OrderItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    Page<OrderItemDto> getAll(Pageable pageable);

    OrderItemDto getOne(Long id);

    OrderItemDto create(OrderItemDto dto);

    OrderItemDto update(Long id, OrderItemDto dto);

    OrderItemDto delete(Long id);
}
