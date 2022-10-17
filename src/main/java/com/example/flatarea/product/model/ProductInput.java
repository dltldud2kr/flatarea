package com.example.flatarea.product.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductInput {

    long id;
    String pName;
    long categoryId;
    String pInfo;
    long price;

    int stockAmount;
    int sellAmount;

    //삭제를 위한
    String idList;



}
