package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityEnum {
    Authority1(1, "Authority Message 1"),
    Authority2(2, "Authority Message 2"),
    Authority3(3, "Authority Message 3"),
    Authority4(4, "Authority Message 4");

    private int authorityNumber;
    private String authorityMessage;

    AuthorityEnum(int authorityNumber, String authorityMessage) {
        this.authorityNumber = authorityNumber;
        this.authorityMessage = authorityMessage;
    }

    public int getAuthorityNumber() {
        return authorityNumber;
    }

    public void setAuthorityNumber(int authorityNumber) {
        this.authorityNumber = authorityNumber;
    }

    public String getAuthorityMessage() {
        return authorityMessage;
    }

    public void setAuthorityMessage(String authorityMessage) {
        this.authorityMessage = authorityMessage;
    }
}
