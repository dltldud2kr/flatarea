package com.example.flatarea.member.dto;

import com.example.flatarea.member.entity.Basket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasketDto {

    Long id;
    String pName;
    long price;
    int purchaseAmount;
    int sum;

    public static List<BasketDto> of (List<Basket> baskets){

        if (baskets != null) {
            List<BasketDto> basketList = new ArrayList<>();
            for(Basket x : baskets) {
                basketList.add(of(x));
            }
            return basketList;
        }
        return null;
    }

    public static BasketDto of(Basket basket){
        return BasketDto.builder()
                .id(basket.getId())
                .pName(basket.getPName())
                .price(basket.getPrice())
                .purchaseAmount(basket.getPurchaseAmount())
                .sum(basket.getSum())
                .build();
    }
}
