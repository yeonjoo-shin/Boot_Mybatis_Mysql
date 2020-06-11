package com.iu.s1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	

	public MemberVO  memberLogin(MemberVO memberVO) throws Exception{
		return memberRepository.memberLogin(memberVO);
	}
	
	public int memberJoin(MemberVO memberVO) throws Exception{
		return memberRepository.memberJoin(memberVO);
	}
	
	
	//검증 메서드
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result= false;//에러가 없다
		
		//1. 기본 제공 검증
		//result = bindingResult.hasErrors(); 이걸 쓰던 밑에 if문을 쓰던 같은 거얌
		if(bindingResult.hasErrors()) {
			result=true;
		
		}else {

		
			//2. pw가 일치하는지 검증
			if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
				bindingResult.rejectValue("pwCheck", "memberVO.password.notSame");
				result=true;
			
			}
			
			//3. id 중복 검증 (db 조회)
			//getid가 없으면  null이 나옴 그래서 nullpointexception이 발생해서 비교하기도전에 에러가 발생함
			MemberVO me = memberRepository.memberIdCheck(memberVO); //ㅡmembervo자체를 가지고 와서 null인지 아닌지를 검사해야함. getid하면 안됨
			if(me!= null) {
				bindingResult.rejectValue("id", "memberVO.id.same" ); //변수명,properties에 변수명
				result=true;
			}
		}
		
		return result;
	}
}
