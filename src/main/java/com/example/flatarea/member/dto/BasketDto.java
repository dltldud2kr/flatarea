package com.example.flatarea.member.dto;

import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.entity.Category;
import com.example.flatarea.member.entity.Basket;
import com.example.flatarea.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BasketDto {



    Long id;

    long productId;
    String userId;
    String productName;   //상품 이름

    LocalDateTime addDt; //추가 일시
    long price;         //상품 가격
    int purchaseAmount;    //구매 수량


// 2개의 of 를 쓴 이유 : BasketServiceImpl 에서 optional 을 이용해서 findAll 로 모든 basket 파일들을 리스트로 불러왔고
    // 그 리스트들을 List<Basket> 를 매개변수로 받는 of로 받은 뒤 basket 을 매개변수로 받는 of 메서드로 Dto 로 변환 뒤
    // List<BasketDto> 리스트를 새로 생성후 리턴

    public static BasketDto of(Basket basket){

        return  BasketDto.builder()
                .id(basket.getId())
                .productName(basket.getProductName())
                .userId(basket.getUserId())
                .addDt(basket.getAddDt())
                .price(basket.getPrice())
                .productId(basket.getProductId())
                .build();
    }

    public static List<BasketDto> of (List<Basket> baskets , String userId) {

        if (baskets != null) {
            List<BasketDto> basketList = new ArrayList<>();
            for (Basket x : baskets) {
                if(x.getUserId().equals(userId))
                basketList.add(of(x));
            }
            return basketList;
        }
        return null;
    }



}
