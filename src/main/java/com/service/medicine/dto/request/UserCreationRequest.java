package com.service.medicine.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @NotBlank(message = "NONE_EMPTY")
    @Size(min = 3, message = "INVALID_USERNAME") // dùng cái key của enum password
    String username;

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
}
