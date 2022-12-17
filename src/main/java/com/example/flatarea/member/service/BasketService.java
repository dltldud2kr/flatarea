package com.example.flatarea.member.service;

import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.entity.Category;
import com.example.flatarea.member.dto.BasketDto;
import com.example.flatarea.member.model.BasketInput;
import com.example.flatarea.member.model.ServiceResult;

import java.util.List;

public interface BasketService {

    /**
     * 장바구니 추가
     */
    boolean add(BasketInput parameter);
    /**
     * 장바구니 리스트
     */
     List<BasketDto> list(String userId);
}
