package com.service.medicine.service;

import com.service.medicine.dto.request.MedicineRequest;
import com.service.medicine.dto.response.MedicineResponse;

import java.util.List;

public interface MedicineService {
    MedicineResponse createMedicine (MedicineRequest request);

    List<MedicineResponse> getAllMedicine();

    MedicineResponse updateMedicine(Long id, MedicineRequest request);

    void deleteMedicine(Long id);
}
