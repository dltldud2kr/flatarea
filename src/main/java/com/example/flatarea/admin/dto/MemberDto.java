package com.example.flatarea.admin.dto;

import com.example.flatarea.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDto {

    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;

    boolean adminYn;
    String userStatus;

    String age;
    String gender;
    LocalDateTime lastUdt;

    //추가 컬럼
    long totalCount;
    long seq;       //페이징 회원번호 NO 부분 관련 필요 변수

    public static MemberDto of(Member member){

        return  MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                .regDt(member.getRegDt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                .build();
    }

}
