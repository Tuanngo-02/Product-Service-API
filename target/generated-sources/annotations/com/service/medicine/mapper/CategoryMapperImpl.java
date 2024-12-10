package com.service.medicine.mapper;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.code( request.getCode() );
        category.name( request.getName() );

        return category.build();
    }

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse.CategoryResponseBuilder categoryResponse = CategoryResponse.builder();

        categoryResponse.code( category.getCode() );
        categoryResponse.name( category.getName() );

        return categoryResponse.build();
    }

    @Override
    public void updateCategory(Category category, CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return;
        }

        category.setCode( categoryRequest.getCode() );
        category.setName( categoryRequest.getName() );
    }
}
