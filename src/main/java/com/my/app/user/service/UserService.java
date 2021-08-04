package com.my.app.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.user.dao.UserDao;
import com.my.app.user.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void save() {
		User user = new User();
		user.setUserId("user1");
		user.setUserName("사용자");
		user.setAge(30);
		user.setCreateDt(new Date());
		user.setUpdateDt(new Date());

		userDao.save(user);
	}

}
