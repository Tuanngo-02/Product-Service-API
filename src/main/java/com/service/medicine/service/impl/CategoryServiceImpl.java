package com.service.medicine.service.impl;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.CategoryMapper;
import com.service.medicine.model.Category;
import com.service.medicine.reponsitory.CategoryReponsitory;
import com.service.medicine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryReponsitory categoryReponsitory;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);

        categoryReponsitory.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryReponsitory.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryByCode(String code) {
        Category categories = categoryReponsitory.findById(code).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return categoryMapper.toCategoryResponse(categories);
    }

    @Override
    public CategoryResponse updateCategory(String code, CategoryRequest request) {
        Category category = categoryReponsitory.findByCode(code).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        categoryMapper.updateCategory(category, request);

        categoryReponsitory.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public void deleteCategory(String code) {
        categoryReponsitory.deleteById(code);
    }

}
