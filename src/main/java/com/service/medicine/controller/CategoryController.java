package com.service.medicine.controller;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategory(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategory())
                .build();
    }

    @PutMapping("/{code}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable String code,@RequestBody CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(code, request))
                .build();
    }

    @DeleteMapping("/{code}")
    ApiResponse<Void> deleteCategory(@PathVariable String code){
        categoryService.deleteCategory(code);
        return ApiResponse.<Void>builder().build();
    }
}
