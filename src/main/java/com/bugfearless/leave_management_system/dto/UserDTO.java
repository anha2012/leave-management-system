package com.bugfearless.leave_management_system.dto;

import com.bugfearless.leave_management_system.utils.enums.Role;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private Role role;
}
