package com.hcc.dtos;


import lombok.Data;

@Data
public class AuthCredentialRequest {
    private String username;
    private String password;
}
