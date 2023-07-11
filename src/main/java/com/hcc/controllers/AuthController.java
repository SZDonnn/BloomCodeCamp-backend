package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.dtos.AuthCredentialResponse;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> login(@RequestBody AuthCredentialRequest request) {
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
            User authenticatedUser = (User) auth.getPrincipal();
            String token = jwtUtil.generateToken(authenticatedUser);

            // Print the authority in the console
            System.out.println(token);
            authenticatedUser.getAuthorities().forEach(authority ->
                    System.out.println("User authority: " + authority.getAuthority()));

            // Create the response DTO with token, username, and authority
            AuthCredentialResponse response = new AuthCredentialResponse(
                    token,
                    authenticatedUser.getUsername(),
                    authenticatedUser.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .findFirst()
                            .orElse(null)
            );

            // Set the token as a response header
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);

            // Return the response DTO with the token in the response header
            return ResponseEntity.ok().headers(headers).body(response);
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

    @PostMapping("validate")
    public ResponseEntity<?> validate(HttpServletRequest request, @AuthenticationPrincipal User user) {
        // Get the token from the Authorization header
        String token = request.getHeader("Authorization");

        // Remove the "Bearer " prefix from the token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Validate the token
        if (jwtUtil.validateToken(token, user)) {
            // Token is valid
            return ResponseEntity.ok().body("Token is valid");
        } else {
            // Token is invalid or expired
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
        }
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