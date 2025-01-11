package com.senkyu.products.mapper;

import com.senkyu.products.dto.CategoryDto;
import com.senkyu.products.entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T02:55:04+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.id() );
        category.setName( categoryDto.name() );
        category.setDescription( categoryDto.description() );

        return category;
    }

    @Override
    public CategoryDto toCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryDto categoryDto = new CategoryDto( id, name, description );

        return categoryDto;
    }

    @Override
    public Category updateWithNull(CategoryDto categoryDto, Category category) {
        if ( categoryDto == null ) {
            return category;
        }

        category.setId( categoryDto.id() );
        category.setName( categoryDto.name() );
        category.setDescription( categoryDto.description() );

        return category;
    }
}
