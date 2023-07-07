package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialResponse;
import com.hcc.dtos.UserRegistrationRequest;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()){
                // Perform user registration logic
                // Create a new User entity based on the registration request data
                User user = new User();
                user.setUsername(request.getUsername());

                // Encode the password using the bcrypt encoder
                String encodedPassword = passwordEncoder.encode(request.getPassword());
                user.setPassword(encodedPassword);

                // Determine the role based on the request or other conditions
                String role = request.getRole(); // Assuming the role is provided in the request

                // Add the corresponding authority to the user
                Authority authority = new Authority("ROLE_" + role.toUpperCase());
                authority.setUser(user);

                // Create a new set of authorities
                Set<Authority> authorities = new HashSet<>();
                authorities.add(authority);

                // Set the updated set of authorities for the user
                user.setAuthorities(authorities);
                user.setCohortStartDate(new Date());

                // Save the user to the database
                User savedUser = userRepository.save(user);

                // Return a successful response with the saved user object
                return ResponseEntity.ok(savedUser);

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only users with the ROLE_REVIEWER authority can register a user.");
        }
    }
}

