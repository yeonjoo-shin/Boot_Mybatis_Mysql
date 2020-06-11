package com.iu.s1.board.qna;

import java.util.List;

import org.apache.tomcat.jni.BIOCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/qna/**/")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	
	@GetMapping("qnaSelect")
	public ModelAndView getSelect(BoardVO boardVO,ModelAndView mv) throws Exception{
		boardVO=qnaService.getSelectOne(boardVO);
		
		mv.addObject("vo",boardVO);
		
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	
	@GetMapping("qnaUpdate")
	public ModelAndView setUpdate(ModelAndView mv, BoardVO boardVO) throws Exception{
		boardVO = qnaService.getSelectOne(boardVO);
		
		mv.addObject("vo",boardVO);
		mv.addObject("path","Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public ModelAndView setUpdate(ModelAndView mv, BoardVO boardVO,RedirectAttributes rd) throws Exception{
		int result = qnaService.setUpdate(boardVO);
		
		mv.setViewName("redirect:./qnaList");
		rd.addFlashAttribute("result",result);
		
		return mv;
	}
	
	@GetMapping("qnaDelete")
	public ModelAndView setDelete(ModelAndView mv,BoardVO boardVO,RedirectAttributes rd) throws Exception{
		int result=qnaService.setDelete(boardVO);
		
		mv.setViewName("redirect:./qnaList");
		rd.addFlashAttribute("result",result);
		return mv;
	}
	
	@GetMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv,BoardVO boardVO) throws Exception{
		
		mv.addObject("vo", boardVO);//부모글의 번호
		mv.setViewName("board/boardWrite");
		mv.addObject("path","Reply");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public String boardReply(ModelAndView mv, QnaVO qnaVO,RedirectAttributes rd) throws Exception{
		int result = qnaService.boardReply(qnaVO);
		
		
		rd.addFlashAttribute("result",result);
		return "redirect:./qnaList";
	}
	
	
	
	
	@GetMapping("qnaWrite")
	public ModelAndView setInsert(ModelAndView mv) throws Exception{
		mv.setViewName("board/boardWrite");
		mv.addObject("path","Write");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public String setInsert(BoardVO boardVO,RedirectAttributes rd, MultipartFile[] files) throws Exception{
		
		
		int result=qnaService.setInsert(boardVO, files);
		qnaService.refUpdate(boardVO);
	
		rd.addFlashAttribute("result",result);
		return "redirect:./qnaList";
	}
	
	
	@GetMapping("qnaList")
	public ModelAndView getSelectList(Pager pager,ModelAndView mv) throws Exception{
		
		List<BoardVO> ar = qnaService.getSelectList(pager);
		
		mv.addObject("list",ar);
		mv.addObject("pager",pager);
		
		mv.setViewName("board/boardList");
		return mv;
		
	}
	
}
