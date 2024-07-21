package com.service.medicine.mapper;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory (CategoryRequest request);

    CategoryResponse toCategoryResponse (Category category);

    void updateCategory(@MappingTarget Category category, CategoryRequest categoryRequest);
}
