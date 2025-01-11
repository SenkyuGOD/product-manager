package com.senkyu.products.service.impl;

import com.senkyu.products.dto.OrderItemDto;
import com.senkyu.products.entity.OrderItem;
import com.senkyu.products.mapper.OrderItemMapper;
import com.senkyu.products.repository.OrderItemRepository;
import com.senkyu.products.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemMapper orderItemMapper;

    private final OrderItemRepository orderItemRepository;

    @Override
    public Page<OrderItemDto> getAll(Pageable pageable) {
        Page<OrderItem> orderItems = orderItemRepository.findAll(pageable);
        return orderItems.map(orderItemMapper::toOrderItemDto);
    }

    @Override
    public OrderItemDto getOne(Long id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        return orderItemMapper.toOrderItemDto(orderItemOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @Override
    public OrderItemDto create(OrderItemDto dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        OrderItem resultOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemDto(resultOrderItem);
    }

    @Override
    public OrderItemDto update(Long id, OrderItemDto dto) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
        orderItemMapper.updateWithNull(dto, orderItem);
        OrderItem resultOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemDto(resultOrderItem);
    }

    @Override
    public OrderItemDto delete(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
        if (orderItem != null) {
            orderItemRepository.delete(orderItem);
        }
        return orderItemMapper.toOrderItemDto(orderItem);
    }
}
