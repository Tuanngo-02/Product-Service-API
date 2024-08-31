package com.service.medicine.controller;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductServiceImpl medicineService;
    private final String DEFAULT_PAGE_NUMBER = "0";
    private final String DEFAULT_PAGE_SIZE = "1";
    private final String DEFAULT_SORT_BY = "price";
    @Operation(summary = "Add new product", description = "Send a request via this API to add new product")
    @PostMapping
    ApiResponse<ProductResponse> createMedicine(@RequestBody ProductRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(medicineService.createMedicine(request))
                .build();
    }
    @Operation(summary = "Get list of products per page", description = "Send a request via this API to get product list by page and pageSize")
    @GetMapping
    ApiResponse<Page<ProductResponse>> getAllMedicine(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
                                                      @RequestParam(defaultValue = DEFAULT_SORT_BY) String sortBy){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return ApiResponse.<Page<ProductResponse>>builder()
                .result(medicineService.getAllMedicine(pageable))
                .build();
    }
    @PutMapping("/{id}")
    ApiResponse<ProductResponse> updateMedicine(@PathVariable Long id, @RequestBody ProductRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(medicineService.updateMedicine(id,request))
                .build();
    }
    @Operation(summary = "Delete product", description = "Send a request via this API to delete product")
    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteMedicine (@PathVariable Long id){
        medicineService.deleteMedicine(id);
        return ApiResponse.<Void>builder().build();
    }
}
