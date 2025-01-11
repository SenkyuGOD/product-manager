package com.senkyu.products.service;

import com.senkyu.products.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerDto> getAll(Pageable pageable);

    CustomerDto getOne(Long id);

    CustomerDto create(CustomerDto dto);

    CustomerDto update(Long id, CustomerDto dto);

    CustomerDto delete(Long id);
}
