package com.bugfearless.leave_management_system.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
