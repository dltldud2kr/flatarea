package com.example.flatarea.product.dto;

import com.example.flatarea.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {


    Long id;
    String pName;     //제품명
    String pInfo;    //상품설명
    String summary;     //요약문구
    long price;         //정가
    int stockAmount;    //재고 수량
    int sellAmount;     //판매 수량
    LocalDateTime regDt; //상품 등록일
    LocalDateTime udtDt; //상품 수정일
    String imagePath;


    long categoryId;
    long brandId;


    //추가 컬럼
    long totalCount;
    long seq;       //페이징 회원번호 NO 부분 관련 필요 변수


    public static ProductDto of(Product product) {

        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .brandId(product.getBrandId())
                .imagePath(product.getImagePath())
                .pName(product.getPName())
                .pInfo(product.getPInfo())
                .summary(product.getSummary())
                .stockAmount(product.getStockAmount())
                .sellAmount(product.getSellAmount())
                .price(product.getPrice())
                .regDt(product.getRegDt())
                .udtDt(product.getUdtDt())
                .build();

        return productDto;
    }

    public static List<ProductDto> of(List<Product> products) {

        if(products == null) {
            return null;
        }

        List<ProductDto> productList = new ArrayList<>();
        for(Product x : products){
            productList.add(ProductDto.of(x));
        }
        return productList;
    }
}
