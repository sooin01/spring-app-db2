package com.my.app.user.service;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.user.dao.UserDao;
import com.my.app.user.entity.User;

@Service
public class UserService {

	private static final Log log = LogFactory.getLog(UserService.class);

	public static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<String>();

	// @Autowired
	private UserDao userDao;

	public UserService() {
		log.info("생성자1");
	}

	// @Inject
	public UserService(UserDao userDao) {
		log.info("생성자2");
		this.userDao = userDao;
	}

	// @PostConstruct
	public void init() {
		log.info("초기화!");
	}

	public void run() {
		// 저장
		// QUEUE.offer("");
		// QUEUE.put(null);

		// 꺼내기
		String poll = QUEUE.poll(); // 기다림
		QUEUE.peek(); // 안기다림

		// 로직처리
	}

	@Transactional
	public void save() {
		// log.debug("안녕1");
		Logger.getLogger("com.my.app").setLevel(Level.DEBUG);
		Logger.getLogger("org.hibernate.SQL").setLevel(Level.DEBUG);
		Logger.getLogger("org.hibernate.event").setLevel(Level.DEBUG);
		Logger.getLogger("org.hibernate.pretty").setLevel(Level.DEBUG);
		// log.debug("안녕2");

		User user = new User();
		user.setUserId("user1");
		user.setUserName("사용자");
		user.setAge(30);
		user.setCreateDt(new Date());
		user.setUpdateDt(new Date());

		userDao.save(user);
	}

}
