package com.example.flatarea.member.controller;


import com.example.flatarea.member.dto.MemberDto;
import com.example.flatarea.member.model.MemberInput;
import com.example.flatarea.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login(){

        return "member/login";
    }


    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model , HttpServletRequest request
    , HttpServletResponse response , MemberInput parameter){

        System.out.println(parameter.toString());

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }


    @GetMapping("/member/info")
    public String memberInfo(Principal principal, Model model){

        String userId = principal.getName();
        System.out.println(userId);
        MemberDto detail = memberService.detail(userId);
        System.out.println(detail.toString());
        model.addAttribute("detail", detail);
        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Principal principal
                                   ,MemberInput parameter
                                   ,Model model){

        String userId = principal.getName();
        parameter.setUserId(userId);
        boolean result = memberService.updateMember(parameter);
        if(!result){
            model.addAttribute("message", "회원정보가 존재하지 않습니다.");
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(Principal principal
            , MemberInput parameter
            , Model model){

        String userId = principal.getName();
        parameter.setUserId(userId);
        boolean result = memberService.updateMemberPassword(parameter);

        if(result != true){
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword(Principal principal, Model model){

        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);
        return "member/password";
    }
}
