package com.service.medicine.service;

import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);

    Page<UserResponse> getUser (Pageable pageable);

    UserResponse getMyInfo();

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);

}
