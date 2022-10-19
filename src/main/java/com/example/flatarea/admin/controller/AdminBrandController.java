package com.example.flatarea.admin.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.model.BrandInput;
import com.example.flatarea.admin.model.CategoryInput;
import com.example.flatarea.admin.model.MemberParam;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminBrandController {

    private final BrandService brandService;

    @GetMapping("/admin/brand/list.do")
    public String list(Model model, MemberParam parameter) {

        List<BrandDto> list = brandService.list();
        model.addAttribute("list", list);


        return "admin/brand/list";
    }

    /**
     * 카테고리 추가 , 삭제 , 수정
     */

    @PostMapping("/admin/brand/add.do")
    public String add(Model model, BrandInput parameter) {

        boolean result = brandService.add(parameter.getBrandName());

        return "redirect:/admin/brand/list.do";
    }

    @PostMapping("/admin/brand/delete.do")
    public String del(Model model, BrandInput parameter){

        boolean result = brandService.del(parameter.getId());

        return "redirect:/admin/brand/list.do";
    }

    @PostMapping("/admin/brand/update.do")
    public String update(Model model, BrandInput parameter){

        boolean result = brandService.update(parameter);

        return "redirect:/admin/brand/list.do";
    }

}