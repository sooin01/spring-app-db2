package com.my.app.user.service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserQueueService {

	private static final Log log = LogFactory.getLog(UserQueueService.class);

	private static final Log suubini = LogFactory.getLog("suubini");

	private static final ConcurrentLinkedQueue<String> QUEUE = new ConcurrentLinkedQueue<>();

	private static final LinkedBlockingQueue<String> BLOCKING_QUEUE = new LinkedBlockingQueue<String>();

	private boolean run;

	public void put(String data) {
		QUEUE.add(data);
		// BLOCKING_QUEUE.put(data);
	}

	@Async
	public void run() {
		log.info("Queue 시작!");

		while (run) {
			// 큐 데이터 존재유무 상관없이 꺼냄
			String data = QUEUE.poll();

			// 큐 데이터 있을때까지 기다리다가 꺼냄
//			try {
//				BLOCKING_QUEUE.take();
//				BLOCKING_QUEUE.poll();
//			} catch (InterruptedException e) {
//			}

			if (data != null) {
				suubini.debug(data);
				run(data);
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}

		log.info("Queue 종료!");
	}

	public void run(String data) {
		// log.debug(data);
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}
