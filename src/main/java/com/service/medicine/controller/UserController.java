package com.service.medicine.controller;

import com.service.medicine.dto.request.UserCreationRequest;
import com.service.medicine.dto.request.UserUpdateRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "User Controller")
public class UserController {

    UserServiceImpl userService;
    private final String DEFAULT_PAGE_NUMBER = "0";
    private final String DEFAULT_PAGE_SIZE = "1";
    private final String DEFAULT_SORT_BY = "firstName";
    @Operation(summary = "Create new User", description = "Send a request via this API to create new user")
    @PostMapping
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .message("create user success")
                .code(HttpStatus.CREATED.value())
                .result(userService.createUser(request))
                .build();
    }

    @Operation(summary = "Get list of users per page by Role Admin", description = "Send a request via this API to get user list by page and pageSize")
    @GetMapping
    ApiResponse<Page<UserResponse>> getUser(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
                                            @RequestParam(defaultValue = DEFAULT_SORT_BY) String sortBy){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return ApiResponse.<Page<UserResponse>>builder()
                .result(userService.getUser(pageable))
                .build();
    }

    @Operation(summary = "Get user detail", description = "Send a request via this API to get user information")
    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @Operation(summary = "Update user", description = "Send a request via this API to update user")
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId,@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId,request))
                .build();
    }

    @Operation(summary = "Delete user", description = "Send a request via this API to delete user")
    @DeleteMapping("/{userId}")
    ApiResponse<Void> deleteUser (@PathVariable String userId){
        userService.deleteUser(userId);
        return ApiResponse.<Void>builder().build();
    }

}
