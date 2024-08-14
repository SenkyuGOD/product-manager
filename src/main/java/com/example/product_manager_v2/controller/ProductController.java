package com.example.product_manager_v2.controller;

import com.example.product_manager_v2.entity.Product;
import com.example.product_manager_v2.exception.ServiceException;
import com.example.product_manager_v2.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            logger.info("Request received to retrieve all products");
            List<Product> products = productService.findAll();
            logger.info("Successfully retrieved all products, count: {}", products.size());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occurred while retrieving all products", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            logger.info("Request received to create a new product: {}", product);
            Product createdProduct = productService.save(product);
            logger.info("Successfully created product with ID: {}", createdProduct.getId());
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (ServiceException e) {
            logger.error("Error occurred while creating a new product", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            logger.info("Request received to update product with ID: {}", id);
            Product updatedProduct = productService.update(id, productDetails);
            if (updatedProduct != null) {
                logger.info("Successfully updated product with ID: {}", updatedProduct.getId());
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                logger.warn("Product with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while updating product with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        try {
            logger.info("Request received to delete product with ID: {}", id);
            boolean isDeleted = productService.deleteById(id);
            if (isDeleted) {
                logger.info("Successfully deleted product with ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Product with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while deleting product with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
