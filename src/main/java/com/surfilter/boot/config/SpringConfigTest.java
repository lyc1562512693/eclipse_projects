package com.surfilter.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.surfilter.boot.ssm.model.TestUser;


@Configuration
public class SpringConfigTest {

	@Bean(name="testUser")
	public TestUser getTestUser(){
		TestUser user = new TestUser();
		user.setName("xiaoming");
		user.setPassword("123123");
		return user;
	}
}
