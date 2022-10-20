package com.example.flatarea.admin.repository;

import com.example.flatarea.admin.entity.Brand;
import com.example.flatarea.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    Optional<List<Brand>> findAllByOrderBySortValueDesc();

}
