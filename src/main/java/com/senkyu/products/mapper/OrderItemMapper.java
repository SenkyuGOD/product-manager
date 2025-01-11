package com.senkyu.products.mapper;

import com.senkyu.products.dto.OrderItemDto;
import com.senkyu.products.entity.OrderItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "orderId", target = "order.id")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @InheritInverseConfiguration(name = "toEntity")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @InheritConfiguration(name = "toEntity")
    OrderItem updateWithNull(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);
}