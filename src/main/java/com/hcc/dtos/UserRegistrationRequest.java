package com.hcc.dtos;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;
}
