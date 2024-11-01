package com.bugfearless.leave_management_system.utils.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    OK(200, "OK"),
    CREATED(201, "Created"),
    NOT_FOUND(404, "Not Found"),
    INVALID_CREDENTIALS(401, "Invalid Credentials"),
    USERNAME_ALREADY_EXISTS(409, "Username Already Exists"),;
    
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;
}
