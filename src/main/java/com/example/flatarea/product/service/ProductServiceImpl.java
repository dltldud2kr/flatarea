package com.example.flatarea.product.service;

import com.example.flatarea.admin.dto.MemberDto;
import com.example.flatarea.product.model.ProductInput;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.entity.Product;
import com.example.flatarea.product.mapper.ProductMapper;
import com.example.flatarea.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public boolean add(ProductInput parameter) {

        Product product = Product.builder()
                .categoryId(parameter.getCategoryId())
                .pName(parameter.getPName())
                .pInfo(parameter.getPInfo())
                .price(parameter.getPrice())
                .stockAmount(parameter.getStockAmount())
                .sellAmount(parameter.getSellAmount())
                .regDt(LocalDateTime.now())
                .build();

        productRepository.save(product);
        return true;
    }

    @Override
    public boolean set(ProductInput parameter) {

        Optional<Product> optionalProduct = productRepository.findById(parameter.getId());
        if (!optionalProduct.isPresent()){
            //수정할 데이터가 없다.
            return false;
        }

        Product product = optionalProduct.get();
        product.setCategoryId(parameter.getCategoryId());
        product.setPName(parameter.getPName());
        product.setPInfo(parameter.getPInfo());
        product.setPrice(parameter.getPrice());
        product.setStockAmount(parameter.getStockAmount());
        product.setSellAmount(parameter.getSellAmount());
        //종료문자열
        product.setUdtDt(LocalDateTime.now());
        productRepository.save(product);

        return true;
    }

    @Override
    public List<ProductDto> list(ProductParam parameter) {

        long totalCount = productMapper.selectListCount(parameter);

        List<ProductDto> list = productMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (ProductDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;

    }

    @Override
    public ProductDto getById(long id) {

        return productRepository.findById(id).map(ProductDto::of).orElse(null);
        //memberServiceImpl의 detail()메소드와 동일

    }

    @Override
    public boolean del(String idList) {

        if(idList != null && idList.length() > 0){
            String[] ids = idList.split(",");
            for(String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e){
                }

                if (id > 0){
                    productRepository.deleteById(id);
                }
            }
        }

        return true;
    }
}
