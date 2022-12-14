package com.example.flatarea.admin.mapper;

import com.example.flatarea.admin.dto.BrandDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {

    List<BrandDto> select(BrandDto parameter);

}
