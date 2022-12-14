package com.example.flatarea.member.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.model.MemberParam;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.member.dto.BasketDto;
import com.example.flatarea.member.model.BasketParam;
import com.example.flatarea.member.service.BasketService;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.repository.ProductRepository;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BasketController {
    private final BasketService basketService;
    private final ProductRepository productRepository;

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @GetMapping("/member/basket/list.do")
    public String list (Model model, MemberParam parameter , ProductParam productParam){   //MemberParam이 필요한가 ?
        List<BasketDto> list = basketService.list();
        model.addAttribute("list",list);

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

    @PostMapping("/member/basket/add.do")
    public String add (Model model, BasketParam parameter){

        basketService.add(parameter.getProductId());

        return "redirect:/product/" + parameter.getProductId();
    }


}
