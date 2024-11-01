package com.bugfearless.leave_management_system.dto;

import com.bugfearless.leave_management_system.utils.enums.StatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private T data;

    public BaseResponse() {
        this.message = StatusCode.OK.getMessage();
    }

    public BaseResponse(StatusCode statusCode) {
        this.message = statusCode.getMessage();

    }
}
