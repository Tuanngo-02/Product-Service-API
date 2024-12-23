package com.service.medicine.service;

import com.service.medicine.dto.response.PageResponse;

import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);

    PageResponse<UserResponse> getUser(int page, int pageSize, String sortBy);

    UserResponse getMyInfo();

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
