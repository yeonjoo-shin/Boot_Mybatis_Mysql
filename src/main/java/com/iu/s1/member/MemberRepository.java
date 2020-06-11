package com.iu.s1.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberRepository {
	
	public  MemberVO memberIdCheck(MemberVO memberVO) throws Exception;
	 
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	public int memberJoin(MemberVO memberVO) throws Exception;
}
