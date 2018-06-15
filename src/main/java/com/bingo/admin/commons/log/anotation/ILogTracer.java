/**
 * 
 */
package com.bingo.admin.commons.log.anotation;

/**
 * 日志跟踪接口
 * 
 * @author 	bingo
 */
public interface ILogTracer {

	/**
	 * 跟踪
	 * 
	 * @param topic		topic
	 * @param msg		消息内容
	 */
	void trace(String topic, String msg);
}
