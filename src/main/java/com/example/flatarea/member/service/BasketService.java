package com.example.flatarea.member.service;

import com.example.flatarea.member.dto.BasketDto;

import java.util.List;

public interface BasketService {

    List<BasketDto> list();
    /**
     * 장바구니 추가
     */
    boolean add (long productId);

    /**
     * 장바구니 수정
     */
    boolean update(BasketDto parameter);

    /**
     * 장바구니 삭제
     */
    boolean del (long id);



}
