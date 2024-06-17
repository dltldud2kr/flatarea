package com.example.flatarea.order.service;

import com.example.flatarea.member.dto.MemberDto;
import com.example.flatarea.member.entity.Basket;
import com.example.flatarea.member.repository.BasketRepository;
import com.example.flatarea.member.repository.MemberRepository;
import com.example.flatarea.order.entity.Order;
import com.example.flatarea.order.model.OrderInput;
import com.example.flatarea.order.repository.OrderRepository;
import com.example.flatarea.product.entity.Product;
import com.example.flatarea.product.repository.ProductRepository;
import com.example.flatarea.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BasketRepository basketRepository;


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
    public boolean add(OrderInput param) {

        Product product = productRepository.findById(param.getProductId())
                .orElseThrow(RuntimeException::new);

        Order order = Order.builder()
                .userId(param.getUserId())
                .regDt(LocalDateTime.now())
                .phone(param.getPhone())
                .recipient(param.getRecipientName())
                .payPrice(product.getPrice())
                .orderId(product.getId())
                .orderRequest(param.getOrderRequest())
                .address(param.getAddr() + " " + param.getAddrDetail())
                .status("주문요청")
                .build();

        orderRepository.save(order);
        return true;
    }
}
