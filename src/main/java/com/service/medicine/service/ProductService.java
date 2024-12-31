package com.service.medicine.service;

import com.service.medicine.dto.response.PageResponse;
import com.service.medicine.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductResponse createMedicine(ProductRequest request) throws Exception;

    PageResponse<ProductResponse> getAllMedicine(String keyword,String category, int page, int pageSize, String sortBy);

    ProductResponse updateMedicine(Long id, ProductRequest request);

    void deleteMedicine(Long id);

    ProductResponse uploadImage(Long id, MultipartFile file) throws Exception;
}
