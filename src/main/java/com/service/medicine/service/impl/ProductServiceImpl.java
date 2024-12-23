package com.service.medicine.service.impl;

import com.service.medicine.dto.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.CategoryResponse;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.ProductMapper;
import com.service.medicine.mapper.SubProductMapper;
import com.service.medicine.model.Category;
import com.service.medicine.model.Product;
import com.service.medicine.reponsitory.ProductRepository;
import com.service.medicine.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    ProductMapper productMapper;

    CategoryServiceImpl categoryService;

    SubProductMapper subProductMapper;

    @Override
    public ProductResponse createMedicine(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) throw new AppException(ErrorCode.PRODUCT_EXISTED);
        CategoryResponse category = categoryService.getCategoryByCode(request.getCategory());
        Category categories = new Category();
        categories.setCode(category.getCode());
        categories.setName(category.getName());
        Product product = subProductMapper.productRequestMapperSub(request, categories);
        return subProductMapper.productResponseMapperSub(productRepository.save(product));
    }

    @Override
    public PageResponse<ProductResponse> getAllMedicine(String keyword, String category, int page, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by(sortBy));
        var products = productRepository.findAllByKeywords(pageable, keyword, category);
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product : products){
            productResponses.add(subProductMapper.productResponseMapperSub(product));
        }
        return PageResponse.<ProductResponse>builder()
                .currentPage(page)
                .pageSize(products.getSize())
                .totalPages(products.getTotalPages())
                .totalElement(products.getTotalElements())
                .data(productResponses)
                .build();
    }

    @Override
    public ProductResponse updateMedicine(Long id, ProductRequest request) {
        Product product =
                productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        productMapper.updateMedicine(product, request);
        return subProductMapper.productResponseMapperSub(productRepository.save(product));
    }

    @Override
    public void deleteMedicine(Long id) {
        productRepository.deleteById(id);
    }
}
