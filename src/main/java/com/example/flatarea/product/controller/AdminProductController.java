package com.example.flatarea.product.controller;

import com.example.flatarea.product.model.ProductInput;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.service.ProductService;
import com.example.flatarea.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminProductController {

    private final ProductService productService;

    @GetMapping("/admin/product/list.do")
    public String list(Model model, ProductParam parameter) {
        parameter.init();
        List<ProductDto> productList = productService.list(parameter);

        long totalCount = 0;

        if(!CollectionUtils.isEmpty(productList)){
            totalCount = productList.get(0).getTotalCount();
        }
        String queryString  = parameter.getQueryString();

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", productList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/product/list";
    }

    /**
     * 제품 등록
     */

    @GetMapping("/admin/product/add.do")
    public String add(Model model) {


        return "admin/product/add";
    }

    @PostMapping("/admin/product/add.do")
    public String addSubmit(Model model, ProductInput parameter) {

        boolean result = productService.add(parameter);

        return "redirect:/admin/product/list.do";
    }

}