package com.iu.home.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	public Integer getIdCheck(MemberVO memberVO) throws Exception;
}
