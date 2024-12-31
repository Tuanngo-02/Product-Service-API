package com.service.medicine.mapper;

import org.springframework.stereotype.Component;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.model.Category;
import com.service.medicine.model.Product;

// custom mapper product
@Component
public class SubProductMapper {
    public ProductResponse productResponseMapperSub(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setAvailableQuantity(product.getAvailableQuantity());
        productResponse.setDescription(product.getDescription());
        if (product.getCategory() != null) {
            productResponse.setCategory(product.getCategory().getName());
        } else {
            productResponse.setCategory(null);
        }
        productResponse.setDob(product.getDob());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setCloudinaryImageId(product.getCloudinaryImageId());
        return productResponse;
    }

    public Product productRequestMapperSub(ProductRequest product, Category category) {
        Product productRequest = new Product();
        productRequest.setName(product.getName());
        productRequest.setDob(product.getDob());
        productRequest.setPrice(product.getPrice());
        productRequest.setDescription(product.getDescription());
        productRequest.setAvailableQuantity(product.getAvailableQuantity());
        productRequest.setCategory(category);
        return productRequest;
    }
}
