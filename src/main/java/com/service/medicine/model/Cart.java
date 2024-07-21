package com.service.medicine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long medicineId;

    String medicineName;

    int quantity;

    float amount;

//    @ManyToOne
//    @JoinColumn(name = "bill_id", referencedColumnName = "id") // thông qua khóa ngoại bill_id
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    Bill bill;
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "bill_id", referencedColumnName = "id")
//private Bill bill;
}
