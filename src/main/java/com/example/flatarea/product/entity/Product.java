package com.example.flatarea.product.entity;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String pName;     //제품명

    @Lob        //텍스트를 많이 잡기 위한 어노테이션
    String pInfo;    //상품설명

    long price;         //상품 가격
    int stockAmount;    //재고 수량
    int sellAmount;     //판매 수량
    LocalDateTime regDt; //상품 등록일
    LocalDateTime udtDt; //상품 수정일

    @Column(length = 1000)
    String summary;     //요약문구


    long categoryId;
    long brandId;   //브랜드명

    String imagePath;






}
