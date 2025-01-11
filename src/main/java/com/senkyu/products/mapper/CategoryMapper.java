package com.senkyu.products.mapper;

import com.senkyu.products.entity.Category;
import com.senkyu.products.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);

    Category updateWithNull(CategoryDto categoryDto, @MappingTarget Category category);
}