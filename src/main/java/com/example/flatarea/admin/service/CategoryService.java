package com.example.flatarea.admin.service;

import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.entity.Category;
import com.example.flatarea.admin.model.CategoryInput;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();

    /**
     * 카테고리 신규추가
     */
    boolean add(String categoryName);

    /**
     * 카테고리 수정
     */
    boolean update(CategoryInput parameter);

    /**
     * 카테고리 삭제
     */
    boolean del(long id);

}
