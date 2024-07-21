package com.service.medicine.mapper;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toMedicine(ProductRequest request);

    ProductResponse toMedicineResponse(Product product);

    void updateMedicine(@MappingTarget Product product, ProductRequest request);
}
