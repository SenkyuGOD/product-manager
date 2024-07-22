package com.example.productmanager.service;

import com.example.productmanager.dao.ProductDAO;
import com.example.productmanager.entity.Product;
import com.example.productmanager.repository.impl.ProductRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepositoryImpl productRepository;

    @Autowired
    public ProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDAO productDAO) {
        Product product = Product.builder()
                .name(productDAO.getName())
                .amount(productDAO.getAmount())
                .build();
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}


