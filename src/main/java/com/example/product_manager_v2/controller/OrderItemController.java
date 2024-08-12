package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.OrderItem;
import com.example.product_manager_v2.service.OrderItemService;
import com.example.product_manager_v2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemService.findAll();
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        try {
            OrderItem createdOrderItem = orderItemService.save(orderItem);
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        try {
            OrderItem orderItem = orderItemService.findById(id);
            if (orderItem != null) {
                orderItem.setOrder(orderItemDetails.getOrder());
                orderItem.setProduct(orderItemDetails.getProduct());
                orderItem.setQuantity(orderItemDetails.getQuantity());
                OrderItem updatedOrderItem = orderItemService.save(orderItem);
                return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        try {
            boolean isDeleted = orderItemService.deleteById(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
