package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.board.BoardVO;
import com.iu.s1.board.notice.NoticeRepository;
import com.iu.s1.board.qna.QnaRepository;
import com.iu.s1.board.qna.QnaVO;
import com.iu.s1.member.MemberVO;

@Component
public class CustomInterceptorPost extends HandlerInterceptorAdapter {
	
		//controller 진입후
		
		@Autowired
		QnaRepository qnaRepository;
	
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
			String method = request.getMethod();
			
			if(method.equals("GET")) {
				
			
				MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
				
				//파라미터로 글의 번호를 꺼내기
				int num =(Integer.parseInt(request.getParameter("num"))) ;
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(num);
				boardVO=qnaRepository.getSelectOne(boardVO);
				
				if(memberVO != null &&boardVO.getWriter().equals(memberVO.getId())) {
					return true;
				}else {
					response.sendRedirect("/");
					return false;
				}
			
			}else {
				return true;
			}
			
		}
	
	
	
	/*
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			System.out.println("jsp 렌더링 후");
		
			
			  boolean check = false; 
			  MemberVO memberVO =(MemberVO)request.getSession().getAttribute("member"); 
			  QnaVO qnaVO = (QnaVO)modelAndView.getModel().get("vo");
				
				
			  String url = request.getRequestURI();//요청에서 url을 빼오기
			  String[] ar = url.split("/"); // 잘라서 필요한 부분만 가지고 와랏
			  
			  for(int i=0;i<ar.length;i++) {
				  System.out.println(ar[2]);
			  }
			 
			 
			  System.out.println(qnaVO.getWriter()+"글 작성자");
			  System.out.println(qnaVO.getNum()+"글 번호");
			  
			  
			  if(ar[2].equals("qnaUpdate")) {
				  
			 
				  if(memberVO !=null&& memberVO.getId().equals(qnaVO.getWriter())) { 
					  check=true;
				  }else { 
					  System.out.println("본인이 작성한 글아님. 수정 불가");
					  response.sendRedirect("/");
				  } //컨트롤러에서 리턴되면 이곳으로 온다.그래서 모델앤뷰로 받아서 꺼내거나 할 수 있음.
			  }
			
		}*/
		
		
		
}
