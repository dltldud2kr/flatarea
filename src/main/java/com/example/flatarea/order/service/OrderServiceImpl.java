package com.example.flatarea.order.service;

import com.example.flatarea.member.dto.MemberDto;
import com.example.flatarea.order.model.OrderInput;
import com.example.flatarea.order.repository.OrderRepository;
import com.example.flatarea.product.entity.Product;
import com.example.flatarea.product.repository.ProductRepository;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    @Override
    public Product productInfo(long productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setPName(product.getPName());
            product.setPrice(product.getPrice());
            //나중에 재고가 없을 때는 구매 못하게, 구매 시에는 재고를 줄이게끔하기

            return product;
        }

        return null;
    }

    @Override
    public boolean add(OrderInput orderInput) {
        return false;
    }
}
