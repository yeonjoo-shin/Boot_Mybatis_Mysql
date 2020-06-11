package com.iu.s1.Schedule;

import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.s1.board.notice.NoticeRepository;

@Component
public class TestSchedule {
	@Autowired
	private NoticeRepository noticeRepository;
	/*
	@Scheduled(fixedDelay = 1000)
	public void delay() throws Exception{
		System.out.println("fixDelay");
		System.out.println(Thread.currentThread().getName());//몇번째 스레드를 사용중이냐 8개중에서(멀티스레드)
		System.out.println(Calendar.getInstance().getTime());
		Random random = new Random();
		int d = random.nextInt(3000)+1000;//0-3000사이의 랜덤한 정수+1000
		Thread.sleep(d);//최대 4000안되게 쉬기
	}
	
	@Scheduled(fixedRate = 1000)
	public void rate() throws Exception{
		System.out.println("rate");
		System.out.println(Thread.currentThread().getName());
		System.out.println(Calendar.getInstance().getTime());
		Random random = new Random();
		int d = random.nextInt(3000)+1000;//0-3000사이의 랜덤한 정수+1000
		Thread.sleep(d);//최대 4000안되게 쉬기
	}
	
	
	
	//@Scheduled(fixedRate = 1000)//1초마다 반복
	//@Scheduled(fixedRateString =  "1000")// 1초마다 반복 위에랑 같음
	@Scheduled(cron = "10 * * * * *")
	public void fixRateSchedule() throws Exception{
		Calendar ca = Calendar.getInstance();
		System.out.println(ca.getTime());
		System.out.println("매분 10초에");
		//Thread.sleep(2000);//2초간 쉬어라
		//noticeRepository.setDelete();이런식으로 사용 
	}*/
}
