package com.service.medicine.service;

import java.util.List;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategoryByCode(String code);

    CategoryResponse updateCategory(String code, CategoryRequest request);

    void deleteCategory(String code);
}
