package com.service.medicine.controller;

import com.service.medicine.dto.request.CategoryRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Add new category", description = "Send a request via this API to add new category")
    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }
    @Operation(summary = "Get list of categories", description = "Send a request via this API to get category list")
    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategory(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategory())
                .build();
    }
    @Operation(summary = "Update category", description = "Send a request via this API to update category")
    @PutMapping("/{code}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable String code,@RequestBody CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(code, request))
                .build();
    }
    @Operation(summary = "Delete category", description = "Send a request via this API to delete role by code")
    @DeleteMapping("/{code}")
    ApiResponse<Void> deleteCategory(@PathVariable String code){
        categoryService.deleteCategory(code);
        return ApiResponse.<Void>builder().build();
    }
}
