package com.service.medicine.service;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse createMedicine (ProductRequest request);

    Page<ProductResponse> getAllMedicine(Pageable pageable);

    ProductResponse updateMedicine(Long id, ProductRequest request);

    void deleteMedicine(Long id);
}
