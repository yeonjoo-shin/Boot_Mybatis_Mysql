package com.iu.s1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iu.s1.util.Pager;

@Controller
public class TestController {
	
	

	
	@GetMapping("/test/select/{num}")
	public void test(@PathVariable String num) {//경로와 변수명이 다를 경우 value 값을 설정해줌
		// /test/select/3
		System.out.println("num : " + num);
		// @PathVariable(value = "num")
	}
	
	@GetMapping("/test/{curPage}/{kind}/{search}/list") //경로
	public void test2(Pager pager) {
		System.out.println("curPage : "+pager.getCurPage());
		System.out.println("kind : "+pager.getKind());
		System.out.println("search : "+pager.getSearch());
		// @PathVariable int curPage, @PathVariable String kind, @PathVariable String search
	}
	
	
	@GetMapping("test/select/{num}/{name}")
	public void test3(@PathVariable String num,@PathVariable String name) {
		System.out.println("num : " + num);
		System.out.println("name : "+ name);
		
	}
}
