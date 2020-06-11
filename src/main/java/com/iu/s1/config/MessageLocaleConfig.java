package com.iu.s1.config;

import java.util.Locale;

import javax.websocket.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageLocaleConfig implements WebMvcConfigurer{
	 
	//인터셉터에서 꺼낼  bean 
	@Bean
	public LocaleResolver localResolver() {
		//1.session를 사용하여 설정
		SessionLocaleResolver sResolver =  new SessionLocaleResolver();
		sResolver.setDefaultLocale(Locale.KOREAN);//기본으로 접속햇을 때 어떤 언어로 할거냐
		
		//Cookie를 사용하여 설정
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		cResolver.setCookieName("lang");
		
		return sResolver;
		//return cResolver;
	}
	
	//실제로 변경해줄 
	@Bean
	public LocaleChangeInterceptor localChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localChangeInterceptor()).addPathPatterns("/*");
		
		//WebMvcConfigurer.super.addInterceptors(registry);
	}

}
