package com.hcc.entities;

import javax.persistence.*;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status")
    private String status;
    @Column(name = "number")
    private Integer number;
    @Column(name = "githubUrl")
    private String githubUrl;
    @Column(name = "branch")
    private String branch;
    @Column(name = "reviewVideoUrl")
    private String reviewVideoUrl;
    @Column(name = "`user`")
    private User user;
    @Column(name = "codeReviewer")
    private User codeReviewer;

    public Assignment() {
    }

    public Assignment(Long id, String status, Integer number, String githubUrl,
                      String branch, String reviewVideoUrl, User user, User codeReviewer) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.codeReviewer = codeReviewer;
    }


}
