package com.senkyu.products.mapper;

import com.senkyu.products.dto.CustomerDto;
import com.senkyu.products.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

    Customer updateWithNull(CustomerDto customerDto, @MappingTarget Customer customer);
}