package com.hcc.dtos;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String role;
}
