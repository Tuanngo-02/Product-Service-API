package com.service.medicine.mapper;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public void updateMedicine(Product product, ProductRequest request) {
        if ( request == null ) {
            return;
        }

        product.setName( request.getName() );
        product.setPrice( request.getPrice() );
        product.setAvailableQuantity( request.getAvailableQuantity() );
        product.setDescription( request.getDescription() );
        product.setDob( request.getDob() );
    }
}
