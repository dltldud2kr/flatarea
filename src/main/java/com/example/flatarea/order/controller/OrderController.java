package com.example.flatarea.order.controller;

import com.example.flatarea.order.model.OrderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {


    @GetMapping("/product/purchase")
    public String productPurchase(Model model, OrderParam parameter) {


        return "product/purchase";
    }

    @PostMapping("/product/purchase")
    public String productPurchase2(Model model, OrderParam parameter) {


        return "product/purchase";
    }
}

