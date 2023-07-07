package com.hcc.services;

import com.hcc.dtos.AssignmentRequest;
import com.hcc.entities.Assignment;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AssignmentService {
    private AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment createAssignment(AssignmentRequest request) {
        Assignment assignment = new Assignment();
        assignment.setBranch(request.getBranch());
        assignment.setReviewVideoUrl(request.getReviewVideoUrl());
        assignment.setGithubUrl(request.getGithubUrl());
        assignment.setNumber(request.getNumber());

        return assignmentRepository.save(assignment);
    }


}
