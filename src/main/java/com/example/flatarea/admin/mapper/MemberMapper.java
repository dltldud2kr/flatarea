package com.example.flatarea.admin.mapper;

import com.example.flatarea.member.dto.MemberDto;
import com.example.flatarea.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);


}
