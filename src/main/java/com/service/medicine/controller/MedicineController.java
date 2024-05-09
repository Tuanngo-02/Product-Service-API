package com.service.medicine.controller;

import com.service.medicine.dto.request.MedicineRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.MedicineResponse;
import com.service.medicine.service.MedicineService;
import com.service.medicine.service.impl.MedicineServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MedicineController {
    MedicineServiceImpl medicineService;

    @PostMapping
    ApiResponse<MedicineResponse> createMedicine(@RequestBody MedicineRequest request){
        return ApiResponse.<MedicineResponse>builder()
                .result(medicineService.createMedicine(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<MedicineResponse>> getAllMedicine(){
        return ApiResponse.<List<MedicineResponse>>builder()
                .result(medicineService.getAllMedicine())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<MedicineResponse> updateMedicine(@PathVariable Long id, @RequestBody MedicineRequest request){
        return ApiResponse.<MedicineResponse>builder()
                .result(medicineService.updateMedicine(id,request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteMedicine (@PathVariable Long id){
        medicineService.deleteMedicine(id);
        return ApiResponse.<Void>builder().build();
    }
}
