package com.iu.s1.board;

import java.util.List;

import com.iu.s1.util.Pager;

public interface BoardRepository {
	
	
	//insert
	public int setInsert(BoardVO boardVO)throws Exception;
	//update
	public int setUpdate(BoardVO boardVO) throws Exception;
	//delete
	public int setDelete(BoardVO boardVO) throws Exception;
	//selectOne
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception;
	//selectList
	public List<BoardVO> getSelectList(Pager pager) throws Exception;
	//totalcount
	public long boardCount(Pager pager) throws Exception;
	//hitUpdate
	
	
}
