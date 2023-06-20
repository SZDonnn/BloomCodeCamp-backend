package com.hcc.controllers;

import com.hcc.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @GetMapping
    public ResponseEntity<List<User>> getAssignmentsByUser() {
        List<User> result = new ArrayList<>();
        User user = new User();
        user.setUsername("Username1");
        user.setPassword("Password1");
        user.setCohortStartDate(new Date());
        result.add(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

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

}
