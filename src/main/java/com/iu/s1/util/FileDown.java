package com.iu.s1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.s1.board.BoardFileVO;
import com.iu.s1.board.notice.noticeFile.NoticeFileVO;
@Component
public class FileDown  extends AbstractView{
	
	@Autowired
	private ResourceLoader resourceLoader; //실제경로 가지고 올
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//1. 저장 경로 확인
		String path ="classpath:/static/";
		path = path + (String)model.get("path");
		BoardFileVO boardFileVO = (NoticeFileVO)model.get("fileVO");//object 로 들어가야하므로 형변환
		
		path = path+boardFileVO.getFileName();
		
		File file=resourceLoader.getResource(path).getFile(); //파일명을 담은 것을 파일 객체로 가지고 오자
		
		//한글 처리
		response.setCharacterEncoding("UTF-8");
		
		//header에 집어넣기. 총 파일의 크기를 넣어주기
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일 이름을 인코딩 처리
		String fileName = URLEncoder.encode(boardFileVO.getOriName(),"UTF-8");//올릴때 이름인 oriname을 가지고 올것, 이것을 인코딩
		
		//header 설정
		response.setHeader("Content-Disposition","attachment;fileName=\""+fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary"); //2진 데이터이다
		
		//HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file);//어느폴더에 어느 파일명을 읽어 올것인가
		//Client로 전송 준비
		OutputStream os = response.getOutputStream();
		
		//실제 전송
		FileCopyUtils.copy(fi, os);//소스, 목적지 fi에서 읽어서 os(client)로 보내라
		
		os.close();
		fi.close();
		
		
	}
}
