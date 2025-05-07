package com.devteria.indentity_service.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

//define error codes
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATGOEIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already existed", HttpStatus.CONFLICT),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_EXIST(1005, "Username does not exist in DB", HttpStatus.NOT_FOUND),
    USERNAME_OR_PASSWORD_IS_WRONG(1006, "Username or password is incorrect", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Authentication failed", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND(1009, "Roles not found", HttpStatus.NOT_FOUND),
    DOB_INVALID(1010, "Your age must be at least {min}", HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode statusCode;

}
