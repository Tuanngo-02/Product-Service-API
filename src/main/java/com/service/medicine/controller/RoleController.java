package com.service.medicine.controller;

import com.service.medicine.dto.request.RoleRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.RoleResponse;
import com.service.medicine.service.impl.RoleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleServiceImpl roleService;

    @Operation(summary = "Add new role", description = "Send a request via this API to create new role")
    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }
    @Operation(summary = "Get list of roles by Role Admin", description = "Send a request via this API to get role list")
    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRole())
                .build();
    }
    @Operation(summary = "Delete role by Role Admin", description = "Send a request via this API to delete role")
    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder().build();
    }
}
