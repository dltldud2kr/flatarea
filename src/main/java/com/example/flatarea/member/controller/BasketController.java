package com.example.flatarea.member.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.member.dto.BasketDto;
import com.example.flatarea.member.model.BasketInput;
import com.example.flatarea.member.service.BasketService;
import com.example.flatarea.product.repository.ProductRepository;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BasketController {
    private final BasketService basketService;
    private final ProductRepository productRepository;

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @PostMapping("/basket/add.do")
    public String add(Model model
            , BasketInput parameter
            , Principal principal) {
        parameter.setUserId(principal.getName());

        boolean result = basketService.add(parameter);

        return "redirect:/product/" + parameter.getProductId();

    }

    @GetMapping("/member/basket/list.do")
    public String list(Model model
            , Principal principal) {

        String userId = principal.getName();
        List<BasketDto> list = basketService.list(userId);

        model.addAttribute("list", list);

        // 카테고리 메뉴바
        int productTotalCount = 0;
        List<BrandDto> brandList = brandService.frontList(BrandDto.builder().build());
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (brandList != null) {
            for (BrandDto x : brandList) {
                productTotalCount += x.getProductCount();
            }
        }


        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        model.addAttribute("productTotalCount", productTotalCount);

        return "member/basket/list";
    }

}
