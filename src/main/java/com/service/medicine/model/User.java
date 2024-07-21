package com.service.medicine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//chuỗi id xuất hiện ngẫu nhiên
    String id;

    String username;

    String password;

    String firstName;

    String lastName;

    LocalDate dob;

    @ManyToMany
    Set<Role> roles;
}
