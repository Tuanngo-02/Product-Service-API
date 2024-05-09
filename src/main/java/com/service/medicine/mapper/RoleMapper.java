package com.service.medicine.mapper;

import com.service.medicine.dto.request.RoleRequest;
import com.service.medicine.dto.response.RoleResponse;
import com.service.medicine.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole (RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
