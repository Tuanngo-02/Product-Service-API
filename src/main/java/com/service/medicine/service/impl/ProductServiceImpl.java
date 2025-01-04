package com.service.medicine.service.impl;

import com.service.medicine.dto.response.CloudinaryResponse;
import com.service.medicine.dto.response.PageResponse;
import com.service.medicine.utils.FileUploadUtil;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    CloudinaryService cloudinaryService;

    @Override
    public ProductResponse createMedicine(ProductRequest request) throws Exception {
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

    @Override
    @Transactional
    public ProductResponse uploadImage(Long id, MultipartFile file) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        //thay đổi ảnh khi đưa ảnh mới vào product
        String cloudinaryImageId = product.getCloudinaryImageId();
        if (org.apache.commons.lang3.StringUtils.isNoneBlank(cloudinaryImageId)){
            cloudinaryService.deleteFile(cloudinaryImageId);
        }

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);
        product.setImageUrl(response.getUrl());
        product.setCloudinaryImageId(response.getId());
        return subProductMapper.productResponseMapperSub(productRepository.save(product));
    }
}
