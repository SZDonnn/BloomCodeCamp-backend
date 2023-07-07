package com.hcc.dtos;

import lombok.Data;

@Data
public class AssignemntResponse {
    private String branch;
    private String reviewVideoUrl;
    private String githubUrl;
    private Integer number;
}
