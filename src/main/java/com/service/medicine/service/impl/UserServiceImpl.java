package com.service.medicine.service.impl;

import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.enums.Roles;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.UserMapper;
import com.service.medicine.model.Role;
import com.service.medicine.model.User;
import com.service.medicine.reponsitory.RoleRepository;
import com.service.medicine.reponsitory.UserRepository;
import com.service.medicine.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var role = new HashSet<Role>();
        role.add(Role.builder()
                        .name(Roles.USER.name())
                        .description("user")
                .build());
        user.setRoles(role);
        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")//TẠO CHỐT CHẶN có role admin ms vào đc phương thức
    public Page<UserResponse> getUser(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
