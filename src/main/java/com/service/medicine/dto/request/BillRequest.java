package com.service.medicine.dto.request;

import com.service.medicine.model.Cart;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
