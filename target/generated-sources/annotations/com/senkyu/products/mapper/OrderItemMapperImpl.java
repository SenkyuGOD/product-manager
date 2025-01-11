package com.senkyu.products.mapper;

import com.senkyu.products.dto.CategoryDto;
import com.senkyu.products.dto.OrderItemDto;
import com.senkyu.products.entity.Order;
import com.senkyu.products.entity.OrderItem;
import com.senkyu.products.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T02:55:04+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toEntity(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProduct( orderItemDtoToProduct( orderItemDto ) );
        orderItem.setOrder( orderItemDtoToOrder( orderItemDto ) );
        orderItem.setId( orderItemDto.id() );
        orderItem.setQuantity( orderItemDto.quantity() );

        return orderItem;
    }

    @Override
    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Long productId = null;
        Long orderId = null;
        Long id = null;
        Integer quantity = null;

        productId = orderItemProductId( orderItem );
        orderId = orderItemOrderId( orderItem );
        id = orderItem.getId();
        quantity = orderItem.getQuantity();

        CategoryDto productCategory = null;

        OrderItemDto orderItemDto = new OrderItemDto( id, orderId, productId, productCategory, quantity );

        return orderItemDto;
    }

    @Override
    public OrderItem updateWithNull(OrderItemDto orderItemDto, OrderItem orderItem) {
        if ( orderItemDto == null ) {
            return orderItem;
        }

        if ( orderItem.getProduct() == null ) {
            orderItem.setProduct( new Product() );
        }
        orderItemDtoToProduct1( orderItemDto, orderItem.getProduct() );
        if ( orderItem.getOrder() == null ) {
            orderItem.setOrder( new Order() );
        }
        orderItemDtoToOrder1( orderItemDto, orderItem.getOrder() );
        orderItem.setId( orderItemDto.id() );
        orderItem.setQuantity( orderItemDto.quantity() );

        return orderItem;
    }

    protected Product orderItemDtoToProduct(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( orderItemDto.productId() );

        return product;
    }

    protected Order orderItemDtoToOrder(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderItemDto.orderId() );

        return order;
    }

    private Long orderItemProductId(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private Long orderItemOrderId(OrderItem orderItem) {
        Order order = orderItem.getOrder();
        if ( order == null ) {
            return null;
        }
        return order.getId();
    }

    protected void orderItemDtoToProduct1(OrderItemDto orderItemDto, Product mappingTarget) {
        if ( orderItemDto == null ) {
            return;
        }

        mappingTarget.setId( orderItemDto.productId() );
    }

    protected void orderItemDtoToOrder1(OrderItemDto orderItemDto, Order mappingTarget) {
        if ( orderItemDto == null ) {
            return;
        }

        mappingTarget.setId( orderItemDto.orderId() );
    }
}
