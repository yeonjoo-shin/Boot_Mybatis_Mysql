package com.iu.s1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableAspectJAutoProxy aop 혹시 안되면 추가 
public class BootMybatisMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootMybatisMysqlApplication.class, args);
	}

}
