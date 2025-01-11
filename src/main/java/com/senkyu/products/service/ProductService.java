package com.senkyu.products.service;

import com.senkyu.products.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> getAll(Pageable pageable);

    ProductDto getOne(Long id);

    ProductDto create(ProductDto dto);

    ProductDto update(Long id, ProductDto dto);

    ProductDto delete(Long id);
}
