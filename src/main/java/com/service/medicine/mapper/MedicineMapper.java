package com.service.medicine.mapper;

import com.service.medicine.dto.request.MedicineRequest;
import com.service.medicine.dto.response.MedicineResponse;
import com.service.medicine.model.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    Medicine toMedicine(MedicineRequest request);

    MedicineResponse toMedicineResponse(Medicine medicine);

    void updateMedicine(@MappingTarget Medicine medicine, MedicineRequest request);
}
