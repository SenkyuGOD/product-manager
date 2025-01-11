package com.senkyu.products.mapper;

import com.senkyu.products.dto.OrderDto;
import com.senkyu.products.entity.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "customerId", target = "customer.id")
    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Order order) {
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
    }

    @Mapping(source = "customer.id", target = "customerId")
    OrderDto toOrderDto(Order order);

    @InheritConfiguration(name = "toEntity")
    Order updateWithNull(OrderDto orderDto, @MappingTarget Order order);
}