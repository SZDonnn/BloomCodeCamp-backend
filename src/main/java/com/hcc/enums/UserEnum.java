package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserEnum {
    UserEnum1(1, "User Message 1"),
    UserEnum2(2, "User Message 2"),
    UserEnum3(3, "User Message 3"),
    UserEnum4(4, "User Message 4");

    private int userNumber;
    private String userMessage;

    UserEnum(int userNumber, String userMessage) {
        this.userNumber = userNumber;
        this.userMessage = userMessage;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
