package com.example.demo.mapper;

import com.example.demo.domain.MemberDTO;

import java.util.List;

public interface MemberMapper {

    int insert(MemberDTO memberDTO);

    MemberDTO findById(int memberId);

    List<MemberDTO> findAll();

    int update(MemberDTO memberDTO);

    int delete(int id);
}