package com.service.medicine.dto.request;

import java.util.List;

import com.service.medicine.model.Cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {
    String orderDescription;

    List<Cart> cartItems;

    String customerName;

    String customerEmail;
}
