package com.service.medicine.model;

import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String orderDescription;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Cart.class)
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    //    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa
    // điểm có nhiều người ở)
    //    // MapopedBy trỏ tới tên biến Address ở trong Person.
    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bill")
    List<Cart> cartItems;

    int totalAmount;

    public Bill(String orderDescription, User user, List<Cart> cartItems, int totalAmount) {
        this.orderDescription = orderDescription;
        this.user = user;
        this.cartItems = cartItems;
        this.totalAmount = totalAmount;
    }
}
