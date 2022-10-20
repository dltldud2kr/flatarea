package com.example.flatarea.product.repository;

import com.example.flatarea.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByBrandId(long brandId);
    Optional<List<Product>> findByCategoryId(long categoryId);


}
