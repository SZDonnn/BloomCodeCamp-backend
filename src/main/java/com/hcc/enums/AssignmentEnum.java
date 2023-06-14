package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {
    Assignment1(1, "Assignment 1"),
    Assignment2(2, "Assignment 2"),
    Assignment3(3, "Assignment 3"),
    Assignment4(4, "Assignment 4");

    private int assignmentNumber;
    private String assignmentMessage;

    AssignmentEnum(int assignmentNumber, String assignmentMessage) {
        this.assignmentNumber = assignmentNumber;
        this.assignmentMessage = assignmentMessage;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public String getAssignmentMessage() {
        return assignmentMessage;
    }

    public void setAssignmentMessage(String assignmentMessage) {
        this.assignmentMessage = assignmentMessage;
    }
}
