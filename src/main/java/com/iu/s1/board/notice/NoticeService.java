package com.iu.s1.board.notice;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.BoardRepository;
import com.iu.s1.board.BoardService;
import com.iu.s1.board.BoardVO;
import com.iu.s1.board.notice.noticeFile.NoticeFileRepository;
import com.iu.s1.board.notice.noticeFile.NoticeFileVO;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
@Transactional
public class NoticeService implements BoardService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Value("${board.notice.filePath}") //properties에 upload/notice로 설정해줫음
	private String filePath;
	
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;//실제 파일을 하드에 저장하는 애
	@Autowired
	private NoticeFileRepository  noticeFileRepository;
	
	@Override
	public int setInsert(BoardVO boardVO,MultipartFile[] files) throws Exception {
		File file = pathGenerator.getUseClassPathResource(filePath);
		
		int result = noticeRepository.setInsert(boardVO); //먼저해서 boardvo에 num이 셋팅될것(AI)
		
		for(MultipartFile multipartFile : files) {
			if(multipartFile.getSize()<=0) {
				continue;
			} //쓸데없는 0바이트 파일이 올라가는 것을 막는 것
			String fileName=fileManager.saveFileCopy(multipartFile, file);//리턴은 실제 저장된 이름
			NoticeFileVO noticeFileVO = new NoticeFileVO();
			noticeFileVO.setFileName(fileName);//카피해서 저장되어 만들어진 이름
			noticeFileVO.setOriName(multipartFile.getOriginalFilename());
			noticeFileVO.setNum(boardVO.getNum()); //미리 셋팅된 num을 가지고와랏
			
			result = noticeFileRepository.setInsert(noticeFileVO);
			
			System.out.println(fileName);
		}
		return result;//;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setDelete(boardVO);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.getSelectOne(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		
		long totalCount = noticeRepository.boardCount(pager);
		
		
		pager.makePage(totalCount);
		
		return noticeRepository.getSelectList(pager);
	}
	
	public NoticeFileVO fileDown(NoticeFileVO noticeFileVO) throws Exception{
		return noticeFileRepository.fileDown(noticeFileVO);
	}
	@Override
	public int refUpdate(BoardVO boardVO) throws Exception{
		return 0;
	}
	
	
}
