package com.example.flatarea.product.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.model.ProductInput;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.service.ProductService;
import com.example.flatarea.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;


    @GetMapping("/product")
    public String list(Model model, ProductParam parameter) {

        List<ProductDto> list = productService.frontList(parameter);
        model.addAttribute("list", list);

        List<BrandDto> brandList = brandService.frontList(BrandDto.builder().build());
        model.addAttribute("brandList",brandList);

        return "product/index";
    }

}