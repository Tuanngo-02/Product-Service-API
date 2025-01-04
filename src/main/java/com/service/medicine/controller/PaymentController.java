package com.service.medicine.controller;

import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.VNPayResponse;
import com.service.medicine.service.impl.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService paymentService;
    @Operation(summary = "Payment via gateway VNPay", description = "Send a request with two parameters to payment via VNPay")
    @GetMapping("/vn-pay")
    public ApiResponse<VNPayResponse> pay(@RequestParam("billID") Long billID,
                                          @RequestParam("bankCode") String bankCode){
        return ApiResponse.<VNPayResponse>builder()
                .result(paymentService.createVnPayPayment(billID, bankCode))
                .build();
    }
    @GetMapping("/vn-pay-callback")
    public ApiResponse<VNPayResponse> payCallbackHandler (HttpServletRequest request){
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")){
            return ApiResponse.<VNPayResponse>builder()
                    .message("success")
                    .build();
        }else {
            return ApiResponse.<VNPayResponse>builder()
                    .message("failed")
                    .build();
        }
    }

}
