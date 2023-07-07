package com.hcc.controllers;

import com.hcc.dtos.AssignmentRequest;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentRequest request,
                                              @AuthenticationPrincipal User user) {
        try {
            // Create a new Assignment entity
            Assignment assignment = new Assignment();
            assignment.setBranch(request.getBranch());
            assignment.setReviewVideoUrl(request.getReviewVideoUrl());
            assignment.setGithubUrl(request.getGithubUrl());
            assignment.setNumber(request.getNumber());
            assignment.setUser(user);

            // Set additional properties if needed (e.g., codeReviewer)

            // Save the assignment to the database
            assignmentRepository.save(assignment);

            // Return a successful response
            return ResponseEntity.ok(assignment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only authenticated users can create an assignment.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable Long id,
                                              @RequestBody AssignmentRequest request,
                                              @AuthenticationPrincipal User user) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);
        if (assignmentOptional.isPresent()) {
            Assignment assignment = assignmentOptional.get();

            // Check if the current user is the code reviewer of the assignment
            // Update the assignment properties
            assignment.setReviewVideoUrl(request.getReviewVideoUrl());
            assignment.setCodeReviewer(user);

            // Save the updated assignment to the database
            Assignment updatedAssignment = assignmentRepository.save(assignment);

            return ResponseEntity.ok(updatedAssignment);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only authenticated users can update an assignment.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable Long id,
                                               @AuthenticationPrincipal User user) {
        // Fetch the assignment from the database based on the provided ID
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);

        if (assignmentOptional.isPresent()) {
            // If the assignment is found, return it in the response
            Assignment assignment = assignmentOptional.get();
            return ResponseEntity.ok(assignment);
        } else {
            // If the assignment is not found, return a not found response
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllAssignments(@AuthenticationPrincipal User user) {
        // Fetch all assignments from the database
        List<Assignment> assignments = assignmentRepository.findAll();

        // Return the assignments in the response
        return ResponseEntity.ok(assignments);
    }
}
