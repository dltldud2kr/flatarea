package com.example.flatarea.purchaseList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PurchaseList {

    @Id
    private Long id;

    private String userId;

    // 기타 필드 및 메서드
}