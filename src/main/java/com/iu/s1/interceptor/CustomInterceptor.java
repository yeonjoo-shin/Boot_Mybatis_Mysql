package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.board.qna.QnaVO;
import com.iu.s1.member.MemberVO;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {
	//controller 진입전
	
	//notice 다 접근 가능
	//write,update,delete 관리자만  //컨트롤러 진입전에
			
	//qna list 누구나 접근가능
	// select, write, reply  회원들만 접근 가능 //컨트롤러 진입전에
	
	//update,delete 작성자만 //빠져나갈때(update)
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		  System.out.println("컨트롤러 진입전");
		  
		  boolean check = false; 
		  MemberVO memberVO =(MemberVO)request.getSession().getAttribute("member");
		  String url = request.getRequestURI();//요청에서 url을 빼오기
		  String[] ar = url.split("/"); // 잘라서 필요한 부분만 가지고 와랏
		  
		  for(int i=0;i<ar.length;i++) {
			  System.out.println(ar[1]);
		  }
		
		 
		
		  if(ar[1].equals("notice")) {
			  
		  
			  if(memberVO !=null&&memberVO.getId().equals("admin")) { 
				  check= true;
				  System.out.println("관리자 계정입니다."); 
			  }else {
				  System.out.println("일반사용자 계정입니다.");
				  response.sendRedirect("../message/messageResult?result=admin이 아닙니다&path=../notice/noticeList"); 
				  }
		  }else {
			  
		 
			  if(memberVO !=null) {
				  check=true;
				  System.out.println("회원입니다.");
			  }else {
				  System.out.println("로그인을 먼저 해주세요.");
				  response.sendRedirect("../member/memberLogin");
			  }
		  }
		  
		  
		  
		return check;
		// return true : 컨트롤러로 보내버리겠다.
		// return false :  진입을 막겠다.redirect 로 전송
	}
	
	
	
}
