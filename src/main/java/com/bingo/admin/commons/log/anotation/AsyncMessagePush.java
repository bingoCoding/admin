/**
 * 
 */
package com.bingo.admin.commons.log.anotation;

import com.bingo.admin.commons.log.mq.MessageSender;
import com.bingo.admin.utils.ExecutorServiceTool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 异步消息推送
 * 
 */
public final class AsyncMessagePush {
	
	/** 执行器 */
	private static final ExecutorService EXECUTOR = ExecutorServiceTool.getExecutor(300);

	/**
	 * 消息推送
	 * 
	 * @param messageSender			消息发送器
	 * @param topic					topic
	 * @param message				消息
	 */
	public static void push(final MessageSender messageSender, final String topic, final String message) {
		EXECUTOR.execute(new Runnable() {
			public void run() {
				messageSender.sendMessage(topic, message);
			}
		});
	}
}
