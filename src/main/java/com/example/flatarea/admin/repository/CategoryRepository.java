package com.example.flatarea.admin.repository;

import com.example.flatarea.admin.entity.Category;
import com.example.flatarea.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<List<Category>> findAllByOrderBySortValueDesc();

}
