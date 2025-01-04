package com.service.medicine.dto.response;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;

    String name;

    int price;

    int availableQuantity;

    String description;

    String category;

    LocalDate dob;

    String imageUrl;

    String cloudinaryImageId;
}
