package com.hcc.dtos;


import lombok.Data;

@Data
public class AuthCredentialRequest {
    private Long id;
    private String token;
    private String username;
    private String password;
}
