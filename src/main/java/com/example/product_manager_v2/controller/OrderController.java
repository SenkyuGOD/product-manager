package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.Order;
import com.example.product_manager_v2.service.OrderService;
import com.example.product_manager_v2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderService.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.save(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        try {
            Order order = orderService.findById(id);
            if (order != null) {
                order.setStatus(orderDetails.getStatus());
                order.setCustomer(orderDetails.getCustomer());
                Order updatedOrder = orderService.save(order);
                return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            boolean isDeleted = orderService.deleteById(id);
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
