package com.service.medicine.service;

import java.util.List;

import com.service.medicine.dto.request.RoleRequest;
import com.service.medicine.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRole();

    void deleteRole(String name);
}
