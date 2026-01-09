package com.example.demo.mapper;

import com.example.demo.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int insert(MemberDTO memberDTO);

    MemberDTO findById(int id);

    List<MemberDTO> findAll();

    int update(MemberDTO memberDTO);

    int delete(int id);
}