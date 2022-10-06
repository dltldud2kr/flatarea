package com.example.flatarea.member.repository;

import com.example.flatarea.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {

}
