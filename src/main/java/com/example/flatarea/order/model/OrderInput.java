package com.example.flatarea.order.model;

import lombok.Data;

@Data
public class OrderInput {

    long productId;
    String recipientName;
    String phone;

    String addr;
    String addrDetail;
    String orderRequest;



}
