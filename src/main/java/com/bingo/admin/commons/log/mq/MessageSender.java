/**
 * 
 */
package com.bingo.admin.commons.log.mq;

import java.util.concurrent.TimeUnit;

import com.bingo.admin.commons.constant.LogTopicConstant;
import com.bingo.admin.utils.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * 消息发送器
 */
@Component
public class MessageSender {

	@Autowired(required = false)
	private KafkaTemplate<String, String> kafkaTemplate;
	
	/** 可发送标志 */
	private volatile boolean canSend = true;
	
	/**
	 * 默认构造函数
	 */
	public MessageSender() {
		ScheduledTask.addTask(new Runnable() {
			@Override
			public void run() {
				if (!canSend) {
					// 测试连接
					send(LogTopicConstant.TOPIC_TEST_CONNECT, "");
				}
			}
		}, 1, TimeUnit.MINUTES);
	}
	
	/**
	 * 发送消息
	 * 
	 * @param topic		topic
	 * @param msg		内容
	 */
	public void sendMessage(String topic, String msg) {
		if (canSend) {
			send(topic, msg);
		}
	}
	
	/**
	 * 发送消息
	 * 
	 * @param topic		topic
	 * @param msg		日志信息
	 */
	private void send(final String topic, final String msg) {
		
		// 避免意外
		if (kafkaTemplate == null) {
			return;
		}
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
		    @Override
		    public void onSuccess(SendResult<String, String> result) {
		    	canSend = true;
		    }
		    @Override
		    public void onFailure(Throwable ex) {
		    	canSend = false;
		    }
		});
	}
}
