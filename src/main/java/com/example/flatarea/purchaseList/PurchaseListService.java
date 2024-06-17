package com.example.flatarea.purchaseList;

import com.example.flatarea.order.entity.Order;

import java.util.List;

public interface PurchaseListService {
    List<Order> getOrdersByUserId(String userId);
}
