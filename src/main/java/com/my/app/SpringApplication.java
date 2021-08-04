package com.my.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.app.config.DefaultConfig;
import com.my.app.user.service.UserService;

public class SpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DefaultConfig.class);

		UserService userService = context.getBean(UserService.class);
		userService.save();
	}

}
