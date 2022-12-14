package com.example.flatarea.product.controller;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;



    @GetMapping(value = {"/", "/product", "/product/**","/product/purchase"})

    public String list(Model model, ProductParam parameter) {

        List<ProductDto> list = productService.frontList(parameter);
        model.addAttribute("list", list);

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

        return "product/index";
    }

    @GetMapping("/product/{id}")

    public String productDetail(Model model, ProductParam parameter) {

        ProductDto detail = productService.frontDetail(parameter.getId());


        model.addAttribute("detail", detail);

        model.addAttribute("list", list(parameter));
        model.addAttribute("brandList", brandList(parameter));
        model.addAttribute("categoryList", categoryList(parameter));
        model.addAttribute("productTotalCount", productTotalCount(brandList(parameter)));
        return "product/detail";
    }


    public List<ProductDto> list(ProductParam parameter) {
        List<ProductDto> list = productService.frontList(parameter);
        return list;
    }

    public List<BrandDto> brandList(ProductParam parameter) {
        List<BrandDto> brandList = brandService.frontList(BrandDto.builder().build());
        return brandList;
    }

    public List<CategoryDto> categoryList(ProductParam parameter) {
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        return categoryList;
    }

    public int productTotalCount(List<BrandDto> brandList) {
        int productTotalCount = 0;
        if (brandList != null) {
            for (BrandDto x : brandList) {
                productTotalCount += x.getProductCount();
            }
        }
        return productTotalCount;
    }

}