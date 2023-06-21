package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
//    @Autowired
//    AssignmentService assignmentService
//    @Autowired
//    UserService userService;

    @GetMapping
//    public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user) {
//        Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
//        return ResponseEntity.ok(assignmentsByUser);
//    }

    @PostMapping
    public String postAssignmentById() {
        return "postAssignmentById";
    }

    @GetMapping("/{id}")
    public String getAssignmentById(@PathVariable Long id) {
        return "getAssignmentById=" + id;
    }

    @PutMapping("/{id}")
    public String updateAssignmentById(@PathVariable Long id) {
        return "updateAssignmentById=" + id;
    }

//    @DeleteMapping("{assignmentId}")
//    public ResponseEntity<?> deleteAssignment(@PathVariable Long assignmentId,
//                                              @AuthenticationPrincipal User user) {
//        return assignmentService.delete(assignmentId);
//    }

//    @PutMapping("{assignmentId}")
//    public ResponseEntity<?> updateAssignment(@PathVariable Long assignmentId,
//                                              @RequestBody Assignment assignment,
//                                              @AuthenticationPrincipal User user) {
//        // logic to decided what role the user is...
//        // if the user has a role of Reviewer then maybe attach that user as the code reviewer of this assignment
//        Assignment updatedAssignment = assignmentService.save(assignment);
//        return ResponseEntity.ok(updatedAssignment);
//    }

}
