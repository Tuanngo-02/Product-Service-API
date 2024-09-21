package com.service.medicine.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    float price;

    int availableQuantity;

    String description;

    LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
