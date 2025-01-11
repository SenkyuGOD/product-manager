package com.senkyu.products.mapper;

import com.senkyu.products.dto.CustomerDto;
import com.senkyu.products.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T02:55:04+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.id() );
        customer.setName( customerDto.name() );
        customer.setEmail( customerDto.email() );

        return customer;
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();

        CustomerDto customerDto = new CustomerDto( id, name, email );

        return customerDto;
    }

    @Override
    public Customer updateWithNull(CustomerDto customerDto, Customer customer) {
        if ( customerDto == null ) {
            return customer;
        }

        customer.setId( customerDto.id() );
        customer.setName( customerDto.name() );
        customer.setEmail( customerDto.email() );

        return customer;
    }
}
