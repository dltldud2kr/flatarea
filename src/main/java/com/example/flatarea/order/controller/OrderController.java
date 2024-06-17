package com.example.flatarea.order.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.member.service.MemberService;
import com.example.flatarea.order.entity.Order;
import com.example.flatarea.order.model.OrderInput;
import com.example.flatarea.order.model.OrderParam;
import com.example.flatarea.order.service.OrderService;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.model.ProductInput;
import com.example.flatarea.product.repository.ProductRepository;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    // OrderParam orderParam,
    @PostMapping("/purchase/{productId}")
    public String purchaseFront( Model model, Principal principal, @PathVariable("productId") long productId){

        model.addAttribute("productId", productId);

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

        return "product/purchase";
    }


//    @GetMapping("/purchase")
//    public String productPurchase(Model model, Principal principal, OrderParam orderParam) {
//
//        List<ProductDto> productInfo = (List<ProductDto>) orderService.productInfo(orderParam.getProductId());
//
//        model.addAttribute("productInfo", productInfo);
//
//        int productTotalCount = 0;
//        List<BrandDto> brandList = brandService.frontList(BrandDto.builder().build());
//        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
//        if (brandList != null) {
//            for (BrandDto x : brandList) {
//                productTotalCount += x.getProductCount();
//            }
//        }
//
//        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("brandList", brandList);
//        model.addAttribute("productTotalCount", productTotalCount);
//
//        return "product/purchase";
//    }

    @PostMapping("/purchase/add.do")
    public String productPurchaseAdd(Model model, OrderInput parameter, Principal principal) {
        String userId = principal.getName();
        parameter.setUserId(userId);

        // 파라미터 객체 로그 출력
        System.out.println("파라미터 로그 체크 ================================" +parameter);

        orderService.add(parameter);



        return "index";
    }
}

