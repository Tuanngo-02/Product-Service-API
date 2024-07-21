package com.service.medicine.service;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.model.Bill;
import com.service.medicine.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategory();

    CategoryResponse updateCategory(String code, CategoryRequest request);

    void deleteCategory(String code);
}

