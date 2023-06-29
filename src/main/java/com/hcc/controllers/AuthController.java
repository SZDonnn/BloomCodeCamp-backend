package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody AuthCredentialRequest request) {
        try {
            // Retrieve the username and password from the request
            String username = request.getUsername();
            String password = request.getPassword();


            // Perform authentication
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Set the authenticated user in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Generate JWT token
            String token = jwtUtil.generateToken((User) auth.getPrincipal());

            // Return the token in the response headers
            return ResponseEntity.ok()
                    .header("Authorization", token)
                    .body(token);
        } catch (AuthenticationException e) {
            // Return 401 Unauthorized if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout() {
        // Invalidate the current session
        request.getSession().invalidate();

        // Return a successful response
        return ResponseEntity.ok().build();
    }

    @GetMapping("validate")
    public ResponseEntity<?> validate(@RequestBody AuthCredentialRequest request) {
        return ResponseEntity.ok().body("validate");
    }

    @GetMapping("userinfo")
    public ResponseEntity<?> userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();

        User user = userRepository.findByUsername(authenticatedUser.getUsername()).orElse(null);

        if (user != null) {
            // Return the user information in the response
            return ResponseEntity.ok(user);
        } else {
            // User not found in the database
            return ResponseEntity.notFound().build();
        }
    }

}
