package com.example.flatarea.admin.dto;

import com.example.flatarea.admin.entity.Brand;
import com.example.flatarea.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandDto {

    Long id;
    String brandName;
    int sortValue;
    boolean usingYn;

    //ADD COLUMNS
    int productCount;

    public static List<BrandDto> of (List<Brand> brands) {

        if (brands != null) {
            List<BrandDto> brandList = new ArrayList<>();
            for (Brand x : brands) {
                brandList.add(of(x));
            }
            return brandList;
        }
        return null;
    }

    public static BrandDto of(Brand brand){
        return BrandDto.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .sortValue(brand.getSortValue())
                .usingYn(brand.isUsingYn())
                .build();
    }
}
