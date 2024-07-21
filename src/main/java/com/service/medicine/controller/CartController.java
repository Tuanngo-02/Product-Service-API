package com.service.medicine.controller;

import com.service.medicine.dto.request.BillRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.BillResponse;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.model.Bill;
import com.service.medicine.model.Cart;
import com.service.medicine.model.User;
import com.service.medicine.service.impl.BillServiceImpl;
import com.service.medicine.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartController {

    BillServiceImpl billService;

    UserServiceImpl userService;

    @GetMapping("/getBill/{billId}")
    public ApiResponse<Bill> getBillDetail(@PathVariable Long billId){
        Bill bill = billService.getBillDetail(billId);
        return ApiResponse.<Bill>builder()
                .result(bill)
                .build();
    }

    @GetMapping("/getMyBill")
    public ApiResponse<Bill> getMyBill(){
        Bill bill = billService.getMyBill();
        return ApiResponse.<Bill>builder()
                .result(Bill.builder()
                        .id(bill.getId())
                        .cartItems(bill.getCartItems())
                        .orderDescription(bill.getOrderDescription())
                        .build())
                .build();
    }

    @PostMapping("/placeBill")
    public ApiResponse<BillResponse> placeBill(@RequestBody BillRequest request){
        float amount = billService.getCartAmount(request.getCartItems());
        UserResponse userResponse = userService.getMyInfo();
        User user = User.builder()
                .id(userResponse.getId())
                .build();
        Bill bill = new Bill(request.getOrderDescription(), user, request.getCartItems());
        bill = billService.saveBill(bill);
        log.info("Order processed successfully..");
        return ApiResponse.<BillResponse>builder()
                .result(BillResponse.builder()
                        .amount(amount)
                        .date("2023-4-4")
                        .invoiceNumber(new Random().nextInt(1000))
                        .billId(bill.getId())
                        .orderDescription(request.getOrderDescription() +" belong to "+ userResponse.getUsername())
                        .build())
                .build();
    }
    @PutMapping("/{userId}")
    public ApiResponse<BillResponse> updateBill(@PathVariable String userId, @RequestBody BillRequest request){
//        float amount = billService.getCartAmountAfterUpdate(request.getCartItems(),userId);
//        UserResponse userResponse = userService.getMyInfo();
//        return ApiResponse.<BillResponse>builder()
//                .result(BillResponse.builder()
//                        .amount(amount)
//                        .date("2023-4-4")
//                        .invoiceNumber(new Random().nextInt(1000))
//                        .billId(1L)
//                        .orderDescription(request.getOrderDescription() +" belong to "+ userResponse.getUsername())
//                        .build())
//                .build();
        float amount = billService.getCartAmountAfterUpdate(request.getCartItems(), userId);

        return ApiResponse.<BillResponse>builder()
                .result(BillResponse.builder()
                        .billId(billService.getMyBill().getId())
                        .amount(amount)
                        .date(String.valueOf(new Date()))
                        .invoiceNumber(new Random().nextInt(1000))
                        .orderDescription(request.getOrderDescription())
                        .build())
                .build();
    }

}
