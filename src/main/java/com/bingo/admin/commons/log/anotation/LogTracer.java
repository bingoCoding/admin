/**
 * 
 */
package com.bingo.admin.commons.log.anotation;

import javax.annotation.Resource;

import com.bingo.admin.commons.log.mq.MessageSender;
import org.springframework.stereotype.Component;


/**
 * 日志跟踪类
 * 
 * @author 	bingo
 */
@Component
public class LogTracer implements ILogTracer {

	/** 消息发送器 */
	@Resource
	private MessageSender messageSender;
	
	/**
	 * 跟踪日志
	 * 
	 * @param topic		topic
	 * @param msg		日志内容
	 */
	@Override
	public void trace(String topic, String msg) {
		AsyncMessagePush.push(messageSender, topic, msg);
	}
}
