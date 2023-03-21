package com.wecodee.SpringBootPractice.common.utils;

public class ThreadUtil {

	public static void executeInNewThread(Runnable runnable) {
		new Thread(runnable).start();
	}

}
