package com.senkyu.products.service.impl;

import com.senkyu.products.dto.ProductDto;
import com.senkyu.products.entity.Product;
import com.senkyu.products.mapper.ProductMapper;
import com.senkyu.products.repository.ProductRepository;
import com.senkyu.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toProductDto);
    }

    @Override
    public ProductDto getOne(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productMapper.toProductDto(productOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @Override
    public ProductDto create(ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        Product resultProduct = productRepository.save(product);
        return productMapper.toProductDto(resultProduct);
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
        productMapper.updateWithNull(dto, product);
        Product resultProduct = productRepository.save(product);
        return productMapper.toProductDto(resultProduct);
    }

    @Override
    public ProductDto delete(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
        return productMapper.toProductDto(product);
    }
}
