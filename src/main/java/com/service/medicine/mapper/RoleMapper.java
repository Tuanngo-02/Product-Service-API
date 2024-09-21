package com.service.medicine.mapper;

import org.mapstruct.Mapper;

import com.service.medicine.dto.request.RoleRequest;
import com.service.medicine.dto.response.RoleResponse;
import com.service.medicine.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
