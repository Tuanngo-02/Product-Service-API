package com.service.medicine.service;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createMedicine (ProductRequest request);

    Page<ProductResponse> getAllMedicine(Pageable pageable);

    ProductResponse updateMedicine(Long id, ProductRequest request);

    void deleteMedicine(Long id);
}
