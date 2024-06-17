package com.example.flatarea.purchaseList;

import com.example.flatarea.order.entity.Order;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PurchaseListController {

    private final PurchaseListService purchaseListService;
    private final ProductService productService;

    @Autowired
    public PurchaseListController(PurchaseListService purchaseListService, ProductService productService) {
        this.purchaseListService = purchaseListService;
        this.productService = productService;
    }

    @GetMapping("/purchaseList")
    public String purchaseList(Model model, ProductParam parameter) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName(); // 현재 인증된 사용자의 ID를 가져옴

        List<Order> orders = purchaseListService.getOrdersByUserId(userId); // 사용자별 주문 목록을 가져오는 메서드
        ProductDto detail = productService.frontDetail(parameter.getId());

        model.addAttribute("orders", orders); // 주문 목록을 모델에 추가
        model.addAttribute("detail", detail);

        return "/member/purchase/purchaseList";
    }




}