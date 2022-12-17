package com.example.flatarea.order.service;

import com.example.flatarea.order.model.OrderInput;
import com.example.flatarea.product.entity.Product;
import org.springframework.stereotype.Service;

public interface OrderService {

    /**
     * 구매제품 정보
     */
    Product productInfo (long productId);

    boolean add (OrderInput orderInput);

}
