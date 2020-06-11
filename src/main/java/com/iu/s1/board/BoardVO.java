package com.iu.s1.board;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BoardVO {
	
	private int num;
	//검증 어노테이션 title이 비어있으면 안돼!
	//@NotEmpty(message = "비어있으면 아니아니아니도ㅣ오")
	@NotEmpty
	@Size(max = 6,min = 3) //문자열의 길이 검증
	private String title;
	@NotEmpty//(message = "이것도 비면 아니되오")
	private String writer;
	@NotEmpty//(message = "아니아니아니되오")
	private String contents;
	private String regDate;
	private int hit;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	

}
