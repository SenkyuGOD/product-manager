package com.senkyu.products.service.impl;

import com.senkyu.products.dto.OrderDto;
import com.senkyu.products.entity.Order;
import com.senkyu.products.mapper.OrderMapper;
import com.senkyu.products.repository.OrderRepository;
import com.senkyu.products.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::toOrderDto);
    }

    @Override
    public OrderDto getOne(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderMapper.toOrderDto(orderOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @Override
    public OrderDto create(OrderDto dto) {
        Order order = orderMapper.toEntity(dto);
        Order resultOrder = orderRepository.save(order);
        return orderMapper.toOrderDto(resultOrder);
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
        orderMapper.updateWithNull(dto, order);
        Order resultOrder = orderRepository.save(order);
        return orderMapper.toOrderDto(resultOrder);
    }

    @Override
    public OrderDto delete(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            orderRepository.delete(order);
        }
        return orderMapper.toOrderDto(order);
    }
}
