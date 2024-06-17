package com.example.flatarea.purchaseList;

import com.example.flatarea.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long> {
    List<Order> findByUserId(String userId);
}
