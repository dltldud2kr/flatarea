package com.example.flatarea.member.service;

import com.example.flatarea.admin.dto.MemberDto;
import com.example.flatarea.admin.model.MemberParam;
import com.example.flatarea.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    /**
     * 회원 등록
     */

    boolean register(MemberInput parameter);

    /**
     * 회원 목록 (관리자 페이지에서 사용)
     */

    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경 (AVAILABLE/STOP)
     */
    boolean ModifyStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     */
    boolean updatePassword(String userId, String password);
}
