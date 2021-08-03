package com.my.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.app.config.DefaultConfig;
import com.my.app.sample.service.SampleService;

public class SpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DefaultConfig.class);

		SampleService sampleService = context.getBean(SampleService.class);
		sampleService.run();
	}

}
