package com.service.medicine.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    @NotBlank(message = "NONE_EMPTY")
    String name;

    @NotBlank(message = "NONE_EMPTY")
    float price;

    int availableQuantity;

    @NotBlank(message = "NONE_EMPTY")
    String category;

    String description;

    LocalDate dob;
}
