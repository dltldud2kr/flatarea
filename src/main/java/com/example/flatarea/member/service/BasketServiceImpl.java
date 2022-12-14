package com.example.flatarea.member.service;

import com.example.flatarea.member.dto.BasketDto;
import com.example.flatarea.member.entity.Basket;
import com.example.flatarea.member.repository.BasketRepository;
import com.example.flatarea.product.entity.Product;
import com.example.flatarea.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;


    @Override
    public List<BasketDto> list() {
        List<Basket> baskets = basketRepository.findAll();
        return BasketDto.of(baskets);
    }

    @Override
    public boolean add(long productId) {

        //같은 장바구니 목록이 있을 시에 purchaseAmount 만을 +1해서 변경

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            Basket basket = Basket.builder()
                    .pName(optionalProduct.get().getPName())
                    .price(optionalProduct.get().getPrice())
                    .purchaseAmount(1)
                    .build();
            basketRepository.save(basket);
            return true;
        }
        return false;
    }




    @Override
    public boolean update(BasketDto parameter) {
        return false;
    }

    @Override
    public boolean del(long id) {
        return false;
    }
}
