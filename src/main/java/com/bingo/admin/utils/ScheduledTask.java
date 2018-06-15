/**
 * 
 */
package com.bingo.admin.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 * 
 */
public final class ScheduledTask {

	/** 定时任务执行服务 */
	private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				executorService.shutdownNow();
			}
		});
	}
	
	/**
	 * 任务只执行一次
	 * 
	 * @param task			任务
	 * @param period		间隔时间
	 * @param unit			时间单位
	 */
	public static void addOneTask(Runnable task, long period, TimeUnit unit) {
		executorService.schedule(task, period, unit);
	}
	
	/**
	 * 添加任务
	 * 
	 * @param task				任务
	 * @param period			间隔时间
	 * @param unit				间隔时间单位
	 */
	public static void addTask(Runnable task, long period, TimeUnit unit) {
		executorService.scheduleAtFixedRate(task, 5, period, unit);
	}
	
	/**
	 * 添加任务
	 * 
	 * @param task				任务
	 * @param initialDelay		第一次延时
	 * @param period			间隔时间
	 * @param unit				间隔时间单位
	 */
	public static void addTask(Runnable task, long initialDelay, long period, TimeUnit unit) {
		executorService.scheduleAtFixedRate(task, initialDelay, period, unit);
	}
}
