package com.service.medicine.service.impl;

import com.service.medicine.dto.request.MedicineRequest;
import com.service.medicine.dto.response.MedicineResponse;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.MedicineMapper;
import com.service.medicine.model.Medicine;
import com.service.medicine.reponsitory.MedicineRepository;
import com.service.medicine.service.MedicineService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MedicineServiceImpl implements MedicineService {

    MedicineRepository medicineRepository;

    MedicineMapper medicineMapper;

    @Override
    public MedicineResponse createMedicine(MedicineRequest request) {
        if (medicineRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.MEDICINE_EXISTED);
        var medicine = medicineMapper.toMedicine(request);

        return medicineMapper.toMedicineResponse(medicineRepository.save(medicine));
    }

    @Override
    public List<MedicineResponse> getAllMedicine() {
        return medicineRepository.findAll().stream().map(medicineMapper::toMedicineResponse).toList();
    }

    @Override
    public MedicineResponse updateMedicine(Long id, MedicineRequest request) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.MEDICINE_NOT_EXISTED));

        medicineMapper.updateMedicine(medicine, request);
        return medicineMapper.toMedicineResponse(medicineRepository.save(medicine));
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
}
