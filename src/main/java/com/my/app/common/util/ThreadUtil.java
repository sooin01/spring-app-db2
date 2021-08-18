package com.my.app.common.util;

import java.lang.reflect.Method;

public class ThreadUtil {

	public static void main(String[] args) throws Exception {
		// 일반적인 방식
		AA aa = new AA();
		aa.run(1);

		// invoke 방식
		AA instance = AA.class.newInstance();
		Method method = AA.class.getDeclaredMethod("run", int.class);
		method.invoke(instance, 2);
	}

}

class AA {

	public void run(int i) {
		for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
			System.out.println(stackTraceElement);
		}
		System.out.println("run! " + i);
	}

}
