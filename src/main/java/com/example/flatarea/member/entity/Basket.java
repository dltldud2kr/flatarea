package com.example.flatarea.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long productId;
    String userId;
    String productName;

    LocalDateTime addDt; //추가 일시
    long price;         //상품 가격
    int purchaseAmount;    //구매 수량


}
