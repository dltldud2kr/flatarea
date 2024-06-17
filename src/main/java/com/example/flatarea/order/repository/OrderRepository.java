package com.example.flatarea.order.repository;


import com.example.flatarea.order.entity.Order;
import com.example.flatarea.order.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByUserId(String userId);
}
