package com.example.flatarea.member.repository;

import com.example.flatarea.member.entity.Basket;
import com.example.flatarea.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    long countByProductIdAndUserId(long productId, String userId);

}