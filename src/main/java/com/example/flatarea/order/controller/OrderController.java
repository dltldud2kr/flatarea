package com.example.flatarea.order.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.member.service.MemberService;
import com.example.flatarea.order.model.OrderParam;
import com.example.flatarea.order.service.OrderService;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.repository.ProductRepository;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final OrderService orderService;
    private final MemberService memberService;

    private final ProductRepository productRepository;


    @GetMapping("/purchase")
    public String productPurchase(Model model, Principal principal, OrderParam orderParam) {

        List<ProductDto> productInfo = (List<ProductDto>) orderService.productInfo(orderParam.getProductId());

        model.addAttribute("productInfo", productInfo);

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

        return "product/purchase";
    }

    @PostMapping("/purchase/add.do")
    public String productPurchase2(Model model, OrderParam parameter) {


        return "";
    }
}

