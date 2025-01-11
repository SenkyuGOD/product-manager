package com.senkyu.products.controller;

import com.senkyu.products.dto.CustomerDto;
import com.senkyu.products.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;


    @GetMapping
    public PagedModel<CustomerDto> getAll(Pageable pageable) {
        Page<CustomerDto> customerDtos = customerService.getAll(pageable);
        return new PagedModel<>(customerDtos);
    }

    @GetMapping("/{id}")
    public CustomerDto getOne(@PathVariable Long id) {
        return customerService.getOne(id);
    }

    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return customerService.create(dto);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @RequestBody CustomerDto dto) {
        return customerService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public CustomerDto delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}
