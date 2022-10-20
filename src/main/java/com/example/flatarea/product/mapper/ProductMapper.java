package com.example.flatarea.product.mapper;

import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    long selectListCount(ProductParam parameter);
    List<ProductDto> selectList(ProductParam parameter);


}
