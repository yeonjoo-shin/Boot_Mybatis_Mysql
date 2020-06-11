package com.iu.s1.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/member/**/")
public class MemeberController {
	
	@Autowired
	private MemberService memberService;
	
	/*@GetMapping("memberIdCheck")
	public ModelAndView memberIdCheck(ModelAndView mv,MemeberVO memeberVO,RedirectAttributes rd) throws Exception{
		memeberVO = memberService.memberIdCheck(memeberVO);
		
		
		mv.setViewName("");
	
		return mv;
	}*/
	
	@GetMapping("memberLogin")
	public ModelAndView memberLogin(ModelAndView mv,MemberVO memberVO)throws Exception{
		mv.addObject("memberVO",memberVO);
		mv.setViewName("member/memberLogin");
		//mv.addObject("path","Login");
		return mv;
	}
	
	
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(ModelAndView mv, MemberVO memberVO,HttpSession session) throws Exception{
		
		memberVO = memberService.memberLogin(memberVO);
		
		if(memberVO !=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}else {
			mv.addObject("result","login fail");
			mv.addObject("path","./memberJoin");
			
			mv.setViewName("member/memberJoin");
		}
		
		
		
		return mv;
	}
	
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin(MemberVO memberVO ,ModelAndView mv) throws Exception{
		mv.setViewName("member/memberJoin");
		mv.addObject("memberVO",memberVO);
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO,BindingResult bindingResult, ModelAndView mv,HttpSession session) throws Exception{//jsp파라미터 이름과 동일한 이름의 avatar를 변수로 해야함.			
		boolean resultt=memberService.memberError(memberVO, bindingResult);
		
		if(resultt) {
			//에러
			mv.addObject("member/memberJoin");
		}else {
		
				int result = memberService.memberJoin(memberVO);

				if(result>0) {
					mv.addObject("result","성공");
					mv.addObject("path","redirect:../");
					mv.setViewName("common/result");
				}else {
					mv.addObject("result","fail");
					mv.addObject("path","memberJoin");
					mv.setViewName("common/result");
				}
			
		}
		
		/*
		if(bindingResult.hasErrors()) {
			mv.addObject("member/memberJoin");
			
		}else {
			int result = memberService.memberJoin(memberVO);
			
			
			
			
			
			if(result>0) {
				mv.addObject("result","성공");
				mv.addObject("path","redirect:../");
				mv.setViewName("common/result");
			}else {
				mv.addObject("result","fail");
				mv.addObject("path","memberJoin");
				mv.setViewName("common/result");
			}
		}*/
			
			 
			 return mv;
	
	}
	
	
	
	
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	
	
	
	
}
