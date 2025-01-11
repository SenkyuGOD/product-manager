package com.senkyu.products.service.impl;

import com.senkyu.products.dto.CustomerDto;
import com.senkyu.products.entity.Customer;
import com.senkyu.products.mapper.CustomerMapper;
import com.senkyu.products.repository.CustomerRepository;
import com.senkyu.products.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    @Override
    public Page<CustomerDto> getAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerMapper::toCustomerDto);
    }

    @Override
    public CustomerDto getOne(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerMapper.toCustomerDto(customerOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @Override
    public CustomerDto create(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        Customer resultCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerDto(resultCustomer);
    }

    @Override
    public CustomerDto update(Long id, CustomerDto dto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
        customerMapper.updateWithNull(dto, customer);
        Customer resultCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerDto(resultCustomer);
    }

    @Override
    public CustomerDto delete(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
        }
        return customerMapper.toCustomerDto(customer);
    }
}
