package com.iu.s1.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class MemberVO {
	//id 비면 안됨
	@NotEmpty
	private String id;
	//비면 안됨
	//4글자 이상 10글자 이하
	@NotEmpty
	@Size(max = 10,min = 4)
	private String pw;
	//0살 이상 200살 미만
	@Range(max = 200,min = 0)
	private Integer age;
	//이메일 형식 아이디@naver.com
	@Email
	private String email;
	
	private String pwCheck;
	
	
	public String getPwCheck() {
		return pwCheck;
	}
	public void setPwCheck(String pwCheck) {
		this.pwCheck = pwCheck;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

}
