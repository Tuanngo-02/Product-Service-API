package com.service.medicine.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    float amount;
     int invoiceNumber;
     String date;
     String orderDescription;
     Long billId;
}
