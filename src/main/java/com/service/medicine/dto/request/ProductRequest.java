package com.service.medicine.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
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
    int price;

    int availableQuantity;

    @NotBlank(message = "NONE_EMPTY")
    String category;

    String description;

    LocalDate dob;
}
