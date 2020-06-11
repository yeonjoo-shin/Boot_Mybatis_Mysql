package com.iu.s1.board.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.BoardService;
import com.iu.s1.board.BoardVO;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
public class QnaService implements BoardService {
	@Autowired
	private QnaRepository  qnaRepository;
	
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {	
		return qnaRepository.setInsert(boardVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.setDelete(boardVO);
	}
	
	public int boardReply(BoardVO boardVO) throws Exception{
		int result = qnaRepository.boardReplyUpdate(boardVO);
		result=qnaRepository.boardReply(boardVO);
		
		return result;
	}
	
	//
	@Override
	public int refUpdate(BoardVO boardVO) throws Exception{
		return qnaRepository.refUpdate(boardVO);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.getSelectOne(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		
		long totalCount=qnaRepository.boardCount(pager);
		
		pager.makePage(totalCount);
			
		return qnaRepository.getSelectList(pager);
	}

}
