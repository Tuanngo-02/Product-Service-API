package com.service.medicine.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayResponse {
    String message;
    int totalAmount;
    String bankCode;
    String paymentUrl;
}
