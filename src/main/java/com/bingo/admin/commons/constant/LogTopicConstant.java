/**
 * 
 */
package com.bingo.admin.commons.constant;

/**
 * LogTopicConstant
 * 
 */
public class LogTopicConstant {

	/** 用于测试连接 */
	public static final String TOPIC_TEST_CONNECT = "TEST_CONNECT";

	/** 访问日志TOPIC */
	public static final String TOPIC_ACCESS_LOG = "ACCESS_LOG";
	
	/** 跟踪日志TOPIC */
	public static final String TOPIC_TRACE_LOG = "TRACE_LOG";
	
	/** 系统统计TOPIC */
	public static final String TOPIC_SYS_SATA_LOG = "SYS_SATA_LOG";
	
	/** 黑名单跟踪TOPIC */
	public static final String TOPIC_BLACK_TRACE_LOG = "BLACK_TRACE_LOG";

	/** 慢SQL TOPIC */
	public static final String TOPIC_SLOW_SQL_LOG = "SLOW_SQL_LOG";

	/** 异常信息 TOPIC */
	public static final String TOPIC_EXCEPTION_LOG = "EXCEPTION_LOG";
	
	/** 应用心跳 TOPIC */
	public static final String TOPIC_APP_HEARTBEAT_LOG = "APP_HEARTBEAT_LOG";
}
