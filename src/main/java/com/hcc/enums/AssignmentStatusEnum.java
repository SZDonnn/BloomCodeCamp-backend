package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentStatusEnum {
    AssignmentStatus1(1, "Assignment Status 1"),
    AssignmentStatus2(2, "Assignment Status 2"),
    AssignmentStatus3(3, "Assignment Status 3"),
    AssignmentStatus4(4, "Assignment Status 4");

    private int assignmentStatusNumber;
    private String assignmentStatusMessage;

    AssignmentStatusEnum(int assignmentStatusNumber, String assignmentStatusMessage) {
        this.assignmentStatusNumber = assignmentStatusNumber;
        this.assignmentStatusMessage = assignmentStatusMessage;
    }

    public int getAssignmentStatusNumber() {
        return assignmentStatusNumber;
    }

    public void setAssignmentStatusNumber(int assignmentStatusNumber) {
        this.assignmentStatusNumber = assignmentStatusNumber;
    }

    public String getAssignmentStatusMessage() {
        return assignmentStatusMessage;
    }

    public void setAssignmentStatusMessage(String assignmentStatusMessage) {
        this.assignmentStatusMessage = assignmentStatusMessage;
    }
}
