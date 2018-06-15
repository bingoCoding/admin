/**
 * 
 */
package com.bingo.admin.commons.log.anotation;

import com.bingo.admin.commons.constant.LogTopicConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Locale;


/**
 * 异常跟踪类
 * 
 */
@Component
public class ExceptionTracer {

	/** 日志跟踪器 */
	@Autowired(required = false)
	private ILogTracer logTracer;
	
	/**
	 * 跟踪未知异常
	 * 
	 * @param errMsg			错误信息
	 * @param methodInfo		错误所在方法（如果能找到）
	 * @param otherInfo			其他信息
	 */
	public void traceUnKnownException(String errMsg, String methodInfo, String otherInfo) {
		
		if (logTracer == null) {
			return;
		}
		
		logTracer.trace(LogTopicConstant.TOPIC_EXCEPTION_LOG,
							generatMessage("U", errMsg, methodInfo, otherInfo));
	}
	
	/**
	 * 跟踪捕捉到的异常
	 * 
	 * @param errMsg			错误信息
	 * @param methodInfo		错误所在方法（如果能找到）
	 * @param otherInfo			其他信息
	 */
	public void traceCatchException(String errMsg, String methodInfo, String otherInfo) {
		
		if (logTracer == null) {
			return;
		}
		
		logTracer.trace(LogTopicConstant.TOPIC_EXCEPTION_LOG,
							generatMessage("C", errMsg, methodInfo, otherInfo));
	}
	
	// 生成消息字符串
	private String generatMessage(String type, String errMsg, String methodInfo, String otherInfo) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("{");
		sb.append("\"type\":\"").append(type).append("\",");
		sb.append("\"date\":\"").append(Calendar.getInstance(Locale.CHINESE)).append("\",");
		sb.append("\"msg\":\"").append(errMsg).append("\",");
		sb.append("\"method\":\"").append(methodInfo).append("\",");
		sb.append("\"info\":\"").append(otherInfo).append("\"");
		sb.append("}");
		return sb.toString();
	}
}
