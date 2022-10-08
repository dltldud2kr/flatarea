package com.example.flatarea.admin.controller;

import com.example.flatarea.admin.dto.MemberDto;
import com.example.flatarea.admin.model.MemberParam;
import com.example.flatarea.admin.model.MemberInput;
import com.example.flatarea.member.service.MemberService;
import com.example.flatarea.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter){

        parameter.init();
        List<MemberDto> members = memberService.list(parameter);

        long totalCount = 0;
        if (members != null && members.size() > 0){
            totalCount = members.get(0).getTotalCount();
        }
        String queryString  = parameter.getQueryString();

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam parameter){

        parameter.init();

        MemberDto member = memberService.detail(parameter.getUserId());
        model.addAttribute("member",member);

        return "admin/member/detail";
    }

    /**
     관리자페이지/회원/회원상세정보/계정상태 수동 변경
     */

    @PostMapping("/admin/member/status.do")
    public String status(Model model, MemberInput parameter){

        boolean result = memberService.ModifyStatus(parameter.getUserId(), parameter.getUserStatus());
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }

    @PostMapping("/admin/member/password.do")
    public String passwordReset(Model model, MemberInput parameter){

        boolean result = memberService.updatePassword(parameter.getUserId(), parameter.getPassword());
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }

}
