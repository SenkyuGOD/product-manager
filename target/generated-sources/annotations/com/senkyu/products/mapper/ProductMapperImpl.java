package com.senkyu.products.mapper;

import com.senkyu.products.dto.CategoryDto;
import com.senkyu.products.dto.ProductDto;
import com.senkyu.products.entity.Category;
import com.senkyu.products.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T02:55:04+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Product toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.id() );
        product.setName( productDto.name() );
        product.setDescription( productDto.description() );
        product.setPrice( productDto.price() );
        product.setCategory( categoryMapper.toEntity( productDto.category() ) );

        return product;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        double price = 0.0d;
        CategoryDto category = null;

        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        category = categoryMapper.toCategoryDto( product.getCategory() );

        ProductDto productDto = new ProductDto( id, name, description, price, category );

        return productDto;
    }

    @Override
    public Product updateWithNull(ProductDto productDto, Product product) {
        if ( productDto == null ) {
            return product;
        }

        product.setId( productDto.id() );
        product.setName( productDto.name() );
        product.setDescription( productDto.description() );
        product.setPrice( productDto.price() );
        if ( productDto.category() != null ) {
            if ( product.getCategory() == null ) {
                product.setCategory( new Category() );
            }
            categoryMapper.updateWithNull( productDto.category(), product.getCategory() );
        }
        else {
            product.setCategory( null );
        }

        return product;
    }
}
