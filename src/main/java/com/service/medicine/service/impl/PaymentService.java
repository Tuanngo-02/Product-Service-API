package com.service.medicine.service.impl;

import com.service.medicine.configuration.VNPayConfig;
import com.service.medicine.dto.response.VNPayResponse;
import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.model.Bill;
import com.service.medicine.reponsitory.BillReponsitory;
import com.service.medicine.utils.VNPayUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    VNPayConfig vnPayConfig;
    BillReponsitory billReponsitory;
public VNPayResponse createVnPayPayment(Long billID, String bankCode) {
    Bill bill = billReponsitory.findById(billID).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_EXISTED));
    long amountBank = bill.getTotalAmount() * 100L;
    Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
    vnpParamsMap.put("vnp_Amount", String.valueOf(amountBank));
    if (bankCode != null && !bankCode.isEmpty()) {
        vnpParamsMap.put("vnp_BankCode", bankCode);
    }
    vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress());
    String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
    String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
    String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
    queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
    String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
    return  VNPayResponse.builder()
            .message("success")
            .totalAmount(bill.getTotalAmount())
            .bankCode(bankCode)
            .paymentUrl(paymentUrl).build();
}
}
