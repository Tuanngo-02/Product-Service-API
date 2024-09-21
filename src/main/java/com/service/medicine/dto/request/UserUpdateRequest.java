package com.service.medicine.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotBlank(message = "NONE_EMPTY")
    @Size(min = 3, message = "PASSWORD_ERROR") // dùng cái key của enum password
    String password;

    @NotBlank(message = "NONE_EMPTY")
    @Size(min = 3, message = "INVALID_NAME") // dùng cái key của enum password
    String firstName;

    @NotBlank(message = "NONE_EMPTY")
    @Size(min = 3, message = "INVALID_NAME") // dùng cái key của enum password
    String lastName;

    LocalDate dob;

    List<String> roles;
}
