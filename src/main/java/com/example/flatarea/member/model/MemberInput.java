package com.example.flatarea.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String newPassword;
    private String phone;
    private String age;
    private String gender;

    private String zipCode;
    private String addr;
    private String addrDetail;
}
