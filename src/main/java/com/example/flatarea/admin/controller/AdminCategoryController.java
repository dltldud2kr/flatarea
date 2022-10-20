package com.example.flatarea.admin.controller;

import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.dto.MemberDto;
import com.example.flatarea.admin.model.CategoryInput;
import com.example.flatarea.admin.model.MemberParam;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam parameter) {

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);


        return "admin/category/list";
    }

    /**
     * 카테고리 추가 , 삭제 , 수정
     */

    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInput parameter) {

        boolean result = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do")
    public String del(Model model, CategoryInput parameter){

        boolean result = categoryService.del(parameter.getId());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/update.do")
    public String update(Model model, CategoryInput parameter){

        boolean result = categoryService.update(parameter);

        return "redirect:/admin/category/list.do";
    }

}