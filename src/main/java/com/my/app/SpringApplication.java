package com.my.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.app.config.DefaultConfig;
import com.my.app.user.service.UserService;

public class SpringApplication {

	private static final Log log = LogFactory.getLog(SpringApplication.class);

	static {
		log.info("메인 실행 전 호출1!");
		init();
	}

	public static void init() {
		log.info("메인 실행 전 호출2!");
	}

	public static void main(String[] args) {
		System.out.println("메인 호출!!");
		log.info("메인 호출!");

		ApplicationContext context = new AnnotationConfigApplicationContext(DefaultConfig.class);

		UserService userService = context.getBean(UserService.class);
		userService.save();
	}

}
