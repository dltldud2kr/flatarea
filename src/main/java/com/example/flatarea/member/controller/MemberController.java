package com.example.flatarea.member.controller;


import com.example.flatarea.admin.dto.MemberDto;
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
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);
        return "member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword(Principal principal, Model model){

        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);
        return "member/password";
    }
}
