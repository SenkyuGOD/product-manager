package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.OrderItem;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        try {
            logger.info("Request received to retrieve all order items");
            List<OrderItem> orderItems = orderItemService.findAll();
            logger.info("Successfully retrieved all order items, count: {}", orderItems.size());
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occurred while retrieving order items", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        try {
            logger.info("Request received to create a new order item: {}", orderItem);
            OrderItem createdOrderItem = orderItemService.save(orderItem);
            logger.info("Successfully created order item with ID: {}", createdOrderItem.getId());
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } catch (ServiceException e) {
            logger.error("Error occurred while creating a new order item", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        try {
            logger.info("Request received to update order item with ID: {}", id);
            OrderItem orderItem = orderItemService.findById(id);
            if (orderItem != null) {
                orderItem.setOrder(orderItemDetails.getOrder());
                orderItem.setProduct(orderItemDetails.getProduct());
                orderItem.setQuantity(orderItemDetails.getQuantity());
                OrderItem updatedOrderItem = orderItemService.save(orderItem);
                logger.info("Successfully updated order item with ID: {}", updatedOrderItem.getId());
                return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
            } else {
                logger.warn("Order item with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while updating order item with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        try {
            logger.info("Request received to delete order item with ID: {}", id);
            boolean isDeleted = orderItemService.deleteById(id);
            if (isDeleted) {
                logger.info("Successfully deleted order item with ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Order item with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while deleting order item with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
