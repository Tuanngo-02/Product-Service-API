package com.service.medicine.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode  {
//    UNCATEGORIZED_EXCEPTION(9999,"UNCATEGORIZED_EXCEPTION",
//            HttpStatus.INTERNAL_SERVER_ERROR),//lỗi ko xác định đc(lỗi 500)
    //lỗi HttpStatus.BAD_REQUEST (lỗi 400 = do người dùng nhập input)
    PASSWORD_ERROR(1004, "Mât khẩu trên {min} chữ số",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1000,"sai tên key enums", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001,"User đã tồn tại",HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1001,"Role đã tồn tại",HttpStatus.BAD_REQUEST),
    MEDICINE_EXISTED(1008,"product đã tồn tại",HttpStatus.BAD_REQUEST),
    INVALID_DOB(1002,"tuổi phải trên {min}",HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1003,"username phải trên {min} kí tự",HttpStatus.BAD_REQUEST),

    USER_NOT_EXISTED(1005,"User ko tồn tại ", HttpStatus.NOT_FOUND),//lỗi 401
    BILL_NOT_EXISTED(1005,"User ko tồn tại ", HttpStatus.NOT_FOUND),//lỗi 401
    ROLE_NOT_EXISTED(1005,"Role ko tồn tại ", HttpStatus.NOT_FOUND),//lỗi 401
    MEDICINE_NOT_EXISTED(1009,"product ko tồn tại ", HttpStatus.NOT_FOUND),//lỗi 401

    UNAUTHENTICATED(1006,"ko xác thực được", HttpStatus.UNAUTHORIZED),//lỗi 401

    UNAUTHORIZED(1007, "ko có quyền truy cập" , HttpStatus.FORBIDDEN)//lỗi 403,
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
