package com.example.flatarea.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Member implements MemberCode{
    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private LocalDateTime lastUdt;  // 회원정보 수정일자
    private LocalDateTime regDt;    // 회원가입 일자

    private boolean adminYn;
    private String userStatus; //정지, 이용가능 상태



}
