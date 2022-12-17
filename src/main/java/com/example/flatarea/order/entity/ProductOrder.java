package com.example.flatarea.order.entity;

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
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long productId; //상품 id
    String userId;

    String recipientName;   //수취인 이름
    String phone;

    long payPrice; //결제 금액

    LocalDateTime orderDt;    //주문일
    String status;  //주문상태(주문요청, 결제완료, 결제취소)

    @Lob        //텍스트를 많이 잡기 위한 어노테이션
    String orderRequest;    //주문 요청사항


    String addr;
    String addrDetail;


}
