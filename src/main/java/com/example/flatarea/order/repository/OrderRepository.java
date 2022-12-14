package com.example.flatarea.order.repository;


import com.example.flatarea.order.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {


}
