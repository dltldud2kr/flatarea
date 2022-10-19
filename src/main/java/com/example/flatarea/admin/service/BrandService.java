package com.example.flatarea.admin.service;

import com.example.flatarea.admin.dto.BrandDto;
import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.model.BrandInput;
import com.example.flatarea.admin.model.CategoryInput;

import java.util.List;

public interface BrandService {

    List<BrandDto> list();

    /**
     * 카테고리 신규추가
     */
    boolean add(String brandName);

    /**
     * 카테고리 수정
     */
    boolean update(BrandInput parameter);

    /**
     * 카테고리 삭제
     */
    boolean del(long id);

    /**
     * 프론트 카테고리 정보
     */
    List<BrandDto> frontList(BrandDto parameter);

}
