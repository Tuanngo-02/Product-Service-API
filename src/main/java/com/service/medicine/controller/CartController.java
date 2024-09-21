package com.service.medicine.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import com.service.medicine.dto.request.BillRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.BillResponse;
import com.service.medicine.dto.response.UserResponse;
import com.service.medicine.model.Bill;
import com.service.medicine.model.User;
import com.service.medicine.service.impl.BillServiceImpl;
import com.service.medicine.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartController {

    BillServiceImpl billService;

    UserServiceImpl userService;

    @Operation(
            summary = "Get list of bill by ID bill",
            description = "Send a request via this API to get list bill by ID bill")
    @GetMapping("/getBill/{billId}")
    public ApiResponse<Bill> getBillDetail(@PathVariable Long billId) {
        Bill bill = billService.getBillDetail(billId);
        return ApiResponse.<Bill>builder().result(bill).build();
    }

    @Operation(summary = "Get list of my bill", description = "Send a request via this API to get list my bill")
    @GetMapping("/getMyBill")
    public ApiResponse<Bill> getMyBill() {
        UserResponse userResponse = userService.getMyInfo();
        Bill bill = billService.getMyBill();
        return ApiResponse.<Bill>builder()
                .result(Bill.builder()
                        .id(bill.getId())
                        .cartItems(bill.getCartItems())
                        .orderDescription(bill.getOrderDescription() + " belong to " + userResponse.getUsername())
                        .build())
                .build();
    }

    @Operation(
            summary = "create new order",
            description = "Send a request via this API to create new order" + "Please input ID product and quantity")
    @PostMapping("/placeBill")
    public ApiResponse<BillResponse> placeBill(@RequestBody BillRequest request) {
        float amount = billService.getCartAmount(request.getCartItems());
        UserResponse userResponse = userService.getMyInfo();
        User user = User.builder().id(userResponse.getId()).build();
        Bill bill = new Bill(request.getOrderDescription(), user, request.getCartItems());
        bill = billService.saveBill(bill);
        log.info("Order processed successfully..");
        return ApiResponse.<BillResponse>builder()
                .result(BillResponse.builder()
                        .amount(amount)
                        .date(String.valueOf(new Date()))
                        .invoiceNumber(new Random().nextInt(1000))
                        .billId(bill.getId())
                        .orderDescription(request.getOrderDescription() + " belong to " + userResponse.getUsername())
                        .build())
                .build();
    }

    @Operation(
            summary = "Update order by ID user",
            description = "Send a request via this API to update order by ID user")
    @PutMapping("/{userId}")
    public ApiResponse<BillResponse> updateBill(@PathVariable String userId, @RequestBody BillRequest request) {
        UserResponse userResponse = userService.getMyInfo();
        float amount = billService.getCartAmountAfterUpdate(request.getCartItems(), userId);

        return ApiResponse.<BillResponse>builder()
                .result(BillResponse.builder()
                        .billId(billService.getMyBill().getId())
                        .amount(amount)
                        .date(String.valueOf(new Date()))
                        .invoiceNumber(new Random().nextInt(1000))
                        .orderDescription(request.getOrderDescription() + " belong to " + userResponse.getUsername())
                        .build())
                .build();
    }
}
