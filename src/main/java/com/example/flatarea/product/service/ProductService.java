package com.example.flatarea.product.service;

import com.example.flatarea.product.model.ProductInput;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.dto.ProductDto;

import java.util.List;

public interface ProductService {

    /**
     * 제품 등록
     */
    boolean add(ProductInput parameter);

    /**
     * 제품 정보 수정
     */
    boolean set(ProductInput parameter);

    /**
     * 제품 목록
     */
    List<ProductDto> list(ProductParam parameter);

    /**
     * 제품 상세 정보
     */
    ProductDto getById(long id);


}
