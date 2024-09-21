package com.service.medicine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(
            9999, "UNCATEGORIZED_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR), // lỗi ko xác định đc(lỗi 500)

    // lỗi HttpStatus.BAD_REQUEST (lỗi 400 = do người dùng nhập input)
    INVALID_KEY(1000, "sai tên key enums", HttpStatus.BAD_REQUEST),

    USER_EXISTED(1001, "User existed", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1001, "Role existed", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1001, "product existed", HttpStatus.BAD_REQUEST),

    INVALID_USERNAME(1002, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(1002, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_NAME(1002, "LastName and FirstName must be at least {min} characters", HttpStatus.BAD_REQUEST),

    NONE_EMPTY(1004, "The input value cannot be blank", HttpStatus.BAD_REQUEST),
    //    INVALID_DOB(1002,"tuổi phải trên {min}",HttpStatus.BAD_REQUEST),

    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND), // lỗi 401
    BILL_NOT_EXISTED(1005, "Bill not existed", HttpStatus.NOT_FOUND), // lỗi 401
    ROLE_NOT_EXISTED(1005, "Role not existed", HttpStatus.NOT_FOUND), // lỗi 401
    PRODUCT_NOT_EXISTED(1005, "Product not existed", HttpStatus.NOT_FOUND), // lỗi 401
    CATEGORY_NOT_EXISTED(1005, "Category not existed", HttpStatus.NOT_FOUND), // lỗi 401

    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED), // lỗi 401

    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN) // lỗi 403,
;

    ErrorCode(int code, String message, HttpStatusCode status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private int code;
    private String message;
    private HttpStatusCode status;
}
