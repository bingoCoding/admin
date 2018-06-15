/**
 * 
 */
package com.bingo.admin.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程执行工具类
 * 
 */
public final class ExecutorServiceTool {
	
	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(ExecutorServiceTool.class);

	/**
	 * 获取默认的线程执行器
	 */
	public static ExecutorService getDefaultExecutor() {
		return Executors.newSingleThreadExecutor();
	}
	
	/**
	 * 获取指定大小的线程执行器
	 */
	public static ExecutorService getExecutor(Object threadCount) {
		int count = 1;
		try {
			count = Integer.parseInt(threadCount.toString());
			if (count == 0) count = 1;
		} catch (Exception e) {
			LOG.warn("param threadCount error with value [{}]", threadCount);
		}
		return Executors.newFixedThreadPool(count);
	}
}
