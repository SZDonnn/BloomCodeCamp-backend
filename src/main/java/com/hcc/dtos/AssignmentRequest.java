package com.hcc.dtos;

import lombok.Data;

@Data
public class AssignmentRequest {
    private String branch;
    private String reviewVideoUrl;
    private String githubUrl;
    private Integer number;
    private String username;
}
