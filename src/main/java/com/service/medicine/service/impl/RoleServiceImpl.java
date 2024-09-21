package com.service.medicine.service.impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.service.medicine.dto.request.RoleRequest;
import com.service.medicine.dto.response.RoleResponse;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.RoleMapper;
import com.service.medicine.model.Role;
import com.service.medicine.reponsitory.RoleRepository;
import com.service.medicine.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        if (roleRepository.existsByName(request.getName())) throw new AppException(ErrorCode.ROLE_EXISTED);

        Role role = roleMapper.toRole(request);

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')") // TẠO CHỐT CHẶN có role admin ms vào đc phương thức
    public List<RoleResponse> getAllRole() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')") // TẠO CHỐT CHẶN có role admin ms vào đc phương thức
    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }
}
