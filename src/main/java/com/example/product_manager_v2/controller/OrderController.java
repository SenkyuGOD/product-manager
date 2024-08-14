package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.Order;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            logger.info("Request to get all orders");
            List<Order> orders = orderService.findAll();
            logger.info("Successfully retrieved {} orders", orders.size());
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error retrieving orders", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            logger.info("Request to create order: {}", order);
            Order createdOrder = orderService.save(order);
            logger.info("Successfully created order with id: {}", createdOrder.getId());
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (ServiceException e) {
            logger.error("Error creating order", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        try {
            logger.info("Request to update order with id: {}", id);
            Order order = orderService.findById(id);
            if (order != null) {
                order.setStatus(orderDetails.getStatus());
                order.setCustomer(orderDetails.getCustomer());
                Order updatedOrder = orderService.save(order);
                logger.info("Successfully updated order with id: {}", updatedOrder.getId());
                return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
            } else {
                logger.warn("Order with id: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error updating order with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            logger.info("Request to delete order with id: {}", id);
            boolean isDeleted = orderService.deleteById(id);
            if (isDeleted) {
                logger.info("Successfully deleted order with id: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Order with id: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error deleting order with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
