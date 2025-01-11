package com.senkyu.products.mapper;

import com.senkyu.products.dto.OrderDto;
import com.senkyu.products.dto.OrderItemDto;
import com.senkyu.products.entity.Customer;
import com.senkyu.products.entity.Order;
import com.senkyu.products.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T02:55:04+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Order toEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setCustomer( orderDtoToCustomer( orderDto ) );
        order.setId( orderDto.id() );
        order.setStatus( orderDto.status() );
        order.setOrderItems( orderItemDtoListToOrderItemList( orderDto.orderItems() ) );

        linkOrderItems( order );

        return order;
    }

    @Override
    public OrderDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        Long customerId = null;
        Long id = null;
        String status = null;
        List<OrderItemDto> orderItems = null;

        customerId = orderCustomerId( order );
        id = order.getId();
        status = order.getStatus();
        orderItems = orderItemListToOrderItemDtoList( order.getOrderItems() );

        OrderDto orderDto = new OrderDto( id, status, customerId, orderItems );

        return orderDto;
    }

    @Override
    public Order updateWithNull(OrderDto orderDto, Order order) {
        if ( orderDto == null ) {
            return order;
        }

        if ( order.getCustomer() == null ) {
            order.setCustomer( new Customer() );
        }
        orderDtoToCustomer1( orderDto, order.getCustomer() );
        order.setId( orderDto.id() );
        order.setStatus( orderDto.status() );
        if ( order.getOrderItems() != null ) {
            List<OrderItem> list = orderItemDtoListToOrderItemList( orderDto.orderItems() );
            if ( list != null ) {
                order.getOrderItems().clear();
                order.getOrderItems().addAll( list );
            }
            else {
                order.setOrderItems( null );
            }
        }
        else {
            List<OrderItem> list = orderItemDtoListToOrderItemList( orderDto.orderItems() );
            if ( list != null ) {
                order.setOrderItems( list );
            }
        }

        linkOrderItems( order );

        return order;
    }

    protected Customer orderDtoToCustomer(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( orderDto.customerId() );

        return customer;
    }

    protected List<OrderItem> orderItemDtoListToOrderItemList(List<OrderItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemDto orderItemDto : list ) {
            list1.add( orderItemMapper.toEntity( orderItemDto ) );
        }

        return list1;
    }

    private Long orderCustomerId(Order order) {
        Customer customer = order.getCustomer();
        if ( customer == null ) {
            return null;
        }
        return customer.getId();
    }

    protected List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemMapper.toOrderItemDto( orderItem ) );
        }

        return list1;
    }

    protected void orderDtoToCustomer1(OrderDto orderDto, Customer mappingTarget) {
        if ( orderDto == null ) {
            return;
        }

        mappingTarget.setId( orderDto.customerId() );
    }
}
