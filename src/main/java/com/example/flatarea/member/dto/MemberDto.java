package com.example.flatarea.member.dto;

import com.example.flatarea.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    LocalDateTime lastUdt;  //회원정보 수정일자

    private String zipCode;
    private String addr;
    private String addrDetail;

    private String platform;

    //추가 컬럼
    long totalCount;
    long seq;       //페이징 회원번호 NO 부분 관련 필요 변수




    public static MemberDto of(Member member){

        return  MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                .regDt(member.getRegDt())
                .lastUdt(member.getLastUdt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())

                .zipCode(member.getZipCode())
                .addr(member.getAddr())
                .addrDetail(member.getAddrDetail())
                .platform(member.getPlatform())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return regDt != null ? regDt.format(formatter) : "";
    }

    public String getLastUdtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return lastUdt != null ? lastUdt.format(formatter) : "";
    }

}
