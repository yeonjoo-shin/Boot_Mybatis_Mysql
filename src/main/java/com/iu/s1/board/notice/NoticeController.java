package com.iu.s1.board.notice;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.s1.board.BoardVO;
import com.iu.s1.board.notice.noticeFile.NoticeFileVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {
		@Autowired
		private NoticeService noticeService;
		
		@ModelAttribute("board")
		public String getBoard() {
			return "notice";
		}
		/*
		//예외처리 메서드
		@ExceptionHandler(NullPointerException.class)//예외객체타입을 설정해줌
		public ModelAndView error() {
			ModelAndView mv = new ModelAndView();
			
			return mv;
		}
		
		//예외처리 메서드
		@ExceptionHandler(TypeMismatchException.class)//예외객체타입을 설정해줌 
		public ModelAndView error2() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("error/serverError");		
			return mv;
		}
		
		@ExceptionHandler(Exception.class)//예외객체타입을 설정해줌
		public ModelAndView error3() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("error/serverError");
			return mv;
		}*/
		
		
		
		
		
		
		
		
		
		@GetMapping("noticeWrite")
		public ModelAndView setInsert() throws Exception{
			ModelAndView mv = new ModelAndView();
			
			mv.addObject("boardVO",new BoardVO());
			mv.setViewName("board/boardWrite");
			mv.addObject("path","Write");
			
			return mv;
		}
		/* boardform
		@PostMapping("noticeWrite")
		public String setInsert(BoardVO boardVO,RedirectAttributes rd,MultipartFile[] files) throws Exception{
			
			int result=noticeService.setInsert(boardVO,files);
			rd.addFlashAttribute("result",result);
			return "redirect:./noticeList"; 
			//리다이렉트하면 모델이 사라져버림
			//부트에서 리다이렉트에서 모델을 집어넣을수 있는법 있음 /리다이렉트할 때 속성을 추가해서 가지고올수잇음 RedirectAttributes
			//이전의 common/result 갔다오는 것과 같은 효과
		}*/
		
		//spring form 처리하는 법
		
		@PostMapping("noticeWrite")
		public ModelAndView setInsert(@Validated BoardVO boardVO,BindingResult bindingResult,RedirectAttributes rd,MultipartFile[] files) throws Exception{
			//BindingResult bindingResult는 검증하고자는 객체 바로 뒤에 들거가야함!!!!!
			ModelAndView mv = new ModelAndView();
			
			if(bindingResult.hasErrors()) {
				mv.setViewName("board/boardWrite");
				mv.addObject("path","Write");
				
				
			}else {
				int result=noticeService.setInsert(boardVO,files);
				rd.addFlashAttribute("result",result);
				mv.setViewName("redirect:./noticeList");
			}
			
			return mv; 
			//리다이렉트하면 모델이 사라져버림
			//부트에서 리다이렉트에서 모델을 집어넣을수 있는법 있음 /리다이렉트할 때 속성을 추가해서 가지고올수잇음 RedirectAttributes
			//이전의 common/result 갔다오는 것과 같은 효과
		}
		
		@GetMapping("noticeList")
		public ModelAndView getSelectList(Pager pager) throws Exception{
			ModelAndView mv = new ModelAndView();
			
			List<BoardVO> ar = noticeService.getSelectList(pager);
			mv.addObject("list",ar);
			mv.addObject("pager",pager);
			mv.setViewName("board/boardList");
			return mv;
		}
		
		@GetMapping("noticeSelect")
		public ModelAndView getSelectOne(BoardVO boardVO)throws Exception{
			ModelAndView mv = new ModelAndView();
			
			boardVO = noticeService.getSelectOne(boardVO);
			mv.addObject("vo",boardVO);
			
			mv.setViewName("board/boardSelect");
			return mv;
		
		}
		
		@GetMapping("noticeUpdate")
		public ModelAndView setUpdate(BoardVO boardVO,ModelAndView mv)throws Exception{
			
			boardVO = noticeService.getSelectOne(boardVO);
			
			mv.addObject("vo",boardVO);
			mv.addObject("path","Update");
			mv.setViewName("board/boardWrite");
			return mv;
			
		}
		
		@PostMapping("noticeUpdate")
		public ModelAndView setUpdate(BoardVO boardVO,RedirectAttributes rd,ModelAndView mv) throws Exception{
			int result = noticeService.setUpdate(boardVO);
			
			mv.setViewName("redirect:./noticeList");
			rd.addFlashAttribute("result",result);
			return mv;
		}
		
		@GetMapping("noticeDelete")
		public ModelAndView setDelete(BoardVO boardVO,ModelAndView mv,RedirectAttributes rd) throws Exception{
			int result = noticeService.setDelete(boardVO);
			mv.setViewName("redirect:./noticeList");
			rd.addFlashAttribute("result",result);
				
		
			return mv;
		}
		
		@GetMapping("fileDown")
		public ModelAndView fileDown(NoticeFileVO noticeFileVO) throws Exception{
			//파라미터로 filenum을 받아올 것임
			
			ModelAndView mv = new ModelAndView();
			
			noticeFileVO = noticeService.fileDown(noticeFileVO);
			System.out.println(noticeFileVO);
			System.out.println(noticeFileVO.getFileName());
			mv.addObject("fileVO",noticeFileVO); //fileDown의 키 값과 같아야함.
			mv.addObject("path","upload/notice/");
			mv.setViewName("fileDown");//이 이름으로 만들억진 객체를 찾아감.
			return mv;
			
			
		}
		
		
		
		
		
		
}
