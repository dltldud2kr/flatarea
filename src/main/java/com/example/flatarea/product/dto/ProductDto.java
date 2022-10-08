package com.example.flatarea.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {


    Long id;
    String pName;     //제품명
    String pInfo;    //상품설명
    long price;         //상품 가격
    int stockAmount;    //재고 수량
    int sellAmount;     //판매 수량
    LocalDateTime regDt; //상품 등록일
    LocalDateTime udtDt; //상품 수정일
    String imagePath;
    long salePrice;     //현재 판매가
    LocalDateTime saleEndDt;    //할인 종료일


    //추가 컬럼
    long totalCount;
    long seq;       //페이징 회원번호 NO 부분 관련 필요 변수


}
