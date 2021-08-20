package com.my.app.user.service;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserQueueService {

	private static final Log log = LogFactory.getLog(UserQueueService.class);

	private static final ConcurrentLinkedQueue<String> QUEUE = new ConcurrentLinkedQueue<>();

	private boolean run;

	public void put(String data) {
		QUEUE.add(data);
	}

	@Async
	public void run() {
		log.info("Queue 시작!");

		while (run) {
			String data = QUEUE.poll();

			if (data != null) {
				run(data);
			}
		}

		log.info("Queue 종료!");
	}

	public void run(String data) {
		log.debug(data);
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}
