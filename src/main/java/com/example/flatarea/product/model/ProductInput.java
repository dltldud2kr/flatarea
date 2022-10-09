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
    long salePrice;
    int stockAmount;
    int sellAmount;
    String saleEndDtText;    //String형


}
