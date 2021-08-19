package com.my.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.my.app.sample.dao.SampleDao;
import com.my.app.sample.service.SampleService;
import com.my.app.user.dao.UserDao;
import com.my.app.user.service.UserService;

@Configuration
public class BeanConfig {

	@Bean
	public SampleService sampleService() {
		return new SampleService();
	}

	@Bean
	public SampleDao sampleDao() {
		return new SampleDao();
	}

	@Bean
	public UserService userService(UserDao userDao) {
		return new UserService(userDao);
	}

	@Bean
	public UserDao userDao() {
		return new UserDao();
	}

}
