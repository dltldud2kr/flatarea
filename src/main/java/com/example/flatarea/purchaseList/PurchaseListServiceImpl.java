package com.example.flatarea.purchaseList;

import com.example.flatarea.order.entity.Order;
import com.example.flatarea.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseListServiceImpl implements PurchaseListService {

private final OrderRepository orderRepository;
    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}