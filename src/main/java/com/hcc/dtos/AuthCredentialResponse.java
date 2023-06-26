package com.hcc.dtos;

import lombok.Data;

@Data
public class AuthCredentialResponse {
    private final String token;
    private final String username;

}
