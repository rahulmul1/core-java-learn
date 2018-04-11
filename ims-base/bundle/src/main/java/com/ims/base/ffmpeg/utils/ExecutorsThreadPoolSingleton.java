package com.ims.base.ffmpeg.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @author Nitishkumar Singh
 * @Description: class will use provide singleton for thread pool
 */
public enum ExecutorsThreadPoolSingleton {

	INSTANCE(Executors.newWorkStealingPool());

	private ExecutorService executor;

	private ExecutorsThreadPoolSingleton(ExecutorService executor) {
		this.executor = executor;
	}

	public static ExecutorService getExecutorService() {
		return INSTANCE.executor;
	}

}
