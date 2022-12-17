package com.example.flatarea.member.service;

import com.example.flatarea.admin.dto.CategoryDto;
import com.example.flatarea.admin.entity.Category;
import com.example.flatarea.member.dto.BasketDto;
import com.example.flatarea.member.entity.Basket;
import com.example.flatarea.member.model.BasketInput;
import com.example.flatarea.member.model.ServiceResult;
import com.example.flatarea.member.repository.BasketRepository;
import com.example.flatarea.product.entity.Product;
import com.example.flatarea.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Override
    public boolean add(BasketInput parameter) {

        Optional<Product> optionalProduct = productRepository.findById(parameter.getProductId());
        if (!optionalProduct.isPresent()) {
            return false;
        }
        Product product = optionalProduct.get();

        Basket basket = Basket.builder()
                .productId(product.getId())
                .productName(product.getPName())
                .userId(parameter.getUserId())
                .price(product.getPrice())
                .addDt(LocalDateTime.now())
                .purchaseAmount(1)
                .build();
        basketRepository.save(basket);

        return true;
    }

    @Override
    public List<BasketDto> list(String userId) {
        List<Basket> baskets = basketRepository.findAll();
        return BasketDto.of(baskets,userId);
    }


}