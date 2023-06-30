package com.hcc.dtos;

import lombok.Data;

@Data
public class UserRegistrationResponse {
    private Long userId;
    private String username;
    private String role;
}
