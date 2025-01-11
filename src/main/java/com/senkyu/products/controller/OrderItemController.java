package com.senkyu.products.controller;

import com.senkyu.products.dto.OrderItemDto;
import com.senkyu.products.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    private final OrderItemService orderItemService;

    @GetMapping
    public PagedModel<OrderItemDto> getAll(Pageable pageable) {
        Page<OrderItemDto> orderItemDtos = orderItemService.getAll(pageable);
        return new PagedModel<>(orderItemDtos);
    }

    @GetMapping("/{id}")
    public OrderItemDto getOne(@PathVariable Long id) {
        return orderItemService.getOne(id);
    }

    @PostMapping
    public OrderItemDto create(@RequestBody OrderItemDto dto) {
        return orderItemService.create(dto);
    }

    @PutMapping("/{id}")
    public OrderItemDto update(@PathVariable Long id, @RequestBody OrderItemDto dto) {
        return orderItemService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public OrderItemDto delete(@PathVariable Long id) {
        return orderItemService.delete(id);
    }
}
