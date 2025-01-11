package com.senkyu.products.service;

import com.senkyu.products.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDto> getAll(Pageable pageable);

    OrderDto getOne(Long id);

    OrderDto create(OrderDto dto);

    OrderDto update(Long id, OrderDto dto);

    OrderDto delete(Long id);
}
