package com.example.flatarea.order.model;

import lombok.Data;

@Data
public class OrderInput {

    long productId;
    String recipientName;

    String phone;
    String recipientPhone;

    String recipientAddr;
    String recipientAddrDetail;
    String orderRequest;



}
