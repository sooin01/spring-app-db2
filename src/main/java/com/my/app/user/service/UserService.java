package com.my.app.user.service;

import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.user.dao.UserDao;
import com.my.app.user.entity.User;

@Service
public class UserService {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	public UserService() {
//		Logger.getLogger("org.hibernate.SQL").setLevel(Level.DEBUG);
	}

	@Transactional
	public void save() {
		Logger.getLogger("org.hibernate.SQL").setLevel(Level.DEBUG);
		Logger.getLogger("org.hibernate.event").setLevel(Level.DEBUG);
		Logger.getLogger("org.hibernate.pretty").setLevel(Level.DEBUG);

		User user = new User();
		user.setUserId("user1");
		user.setUserName("사용자");
		user.setAge(30);
		user.setCreateDt(new Date());
		user.setUpdateDt(new Date());

		userDao.save(user);
	}

}
