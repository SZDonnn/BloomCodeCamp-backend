package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthCredentialRequest request) {

        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String username = request.getUsername();
            String password = passwordEncoder.encode(request.getPassword());
//            System.out.println("PASSWORD " + password);
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            User user = (User) auth.getPrincipal();

            // If authentication successful, generate JWT token
            String token = jwtUtil.generateToken(user);
//            System.out.println("TOKEN = " + token);
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("validate")
    public ResponseEntity<?> validate(@RequestBody AuthCredentialRequest request) {
        return ResponseEntity.ok().body("validate");
    }

}
