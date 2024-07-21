package com.service.medicine.service.impl;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.ProductMapper;
import com.service.medicine.model.Product;
import com.service.medicine.reponsitory.ProductRepository;
import com.service.medicine.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    ProductMapper productMapper;

    @Override
    public ProductResponse createMedicine(ProductRequest request) {
        if (productRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.MEDICINE_EXISTED);
        var medicine = productMapper.toMedicine(request);

        return productMapper.toMedicineResponse(productRepository.save(medicine));
    }

    @Override
    public Page<ProductResponse> getAllMedicine(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toMedicineResponse);
    }

    @Override
    public ProductResponse updateMedicine(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.MEDICINE_NOT_EXISTED));

        productMapper.updateMedicine(product, request);
        return productMapper.toMedicineResponse(productRepository.save(product));
    }

    @Override
    public void deleteMedicine(Long id) {
        productRepository.deleteById(id);
    }
}
