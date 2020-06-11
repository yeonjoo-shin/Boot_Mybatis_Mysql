package com.iu.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iu.s1.interceptor.CustomInterceptor;
import com.iu.s1.interceptor.CustomInterceptorPost;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	//springboot 2.0 이후부터는  Deprecated
	//WebMvcConfigurerAdapter
	
	//springboot 2.0 이후는 다음을 사용
	//WebMvcConfigurer
	
	
	
	
	@Autowired
	private CustomInterceptor customInterceptor; //어떤 url이 왔을 때 이걸 할거냐 //인터셉터 역할
	@Autowired
	private CustomInterceptorPost   customInterceptorPost;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) { //인터셉터에 관한 정보를 가지고 있는   refistry
		//registry.addInterceptor(customInterceptorPost)
		//.addPathPatterns("/qna/qnaUpdate")
		//.addPathPatterns("/qna/qnaDelete");
		
		//적용할 interceptor 등록
		registry.addInterceptor(customInterceptor)
		//인터셉터를 사용할 url 등록 //커스텀 인터셉터를 거치겠다.
		//.addPathPatterns("/notice/*")
		//.addPathPatterns("/member/*")
		.addPathPatterns("/qna/*")
		
		//interceptor에서 제외할 url 등록
		
		.excludePathPatterns("/notice/noticeList") // 커스텀인터셉터를 거치지 않겠다
		.excludePathPatterns("/member/memberLogin")
		.excludePathPatterns("/member/memberLogout")
		.excludePathPatterns("/qna/qnaList");
		
		
		
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
