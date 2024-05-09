package com.service.medicine.mapper;

import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (UserCreationRequest request);

    UserResponse toUserResponse(User user);
    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
