package com.example.flatarea.product.dto;

import com.example.flatarea.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {


    Long id;
    String pName;     //제품명
    String pInfo;    //상품설명
    long price;         //정가
    long salePrice;     //판매가
    int stockAmount;    //재고 수량
    int sellAmount;     //판매 수량
    LocalDateTime regDt; //상품 등록일
    LocalDateTime udtDt; //상품 수정일
    String imagePath;
    LocalDateTime saleEndDt;    //할인 종료일

    long categoryId;


    //추가 컬럼
    long totalCount;
    long seq;       //페이징 회원번호 NO 부분 관련 필요 변수


    public static ProductDto of(Product product) {

        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .imagePath(product.getImagePath())
                .pName(product.getPName())
                .pInfo(product.getPInfo())
                .stockAmount(product.getStockAmount())
                .sellAmount(product.getSellAmount())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .saleEndDt(product.getSaleEndDt())
                .regDt(product.getRegDt())
                .udtDt(product.getUdtDt())
                .build();

        return productDto;
    }
}
