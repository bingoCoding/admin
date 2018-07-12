/**
 * 
 */
package com.bingo.admin.commons.exception;

/**
 * 线程缓存，线程内共享
 */
public final class ThreadCache {

	/** 请求URI */
	private static final ThreadLocal<String> TL_URI = new ThreadLocal<>();
	
	/**
	 * 设置URI
	 */
	public static void setUri(String uri) {
		TL_URI.set(uri);
	}
	
	/**
	 * 获取URI
	 */
	public static String getUri() {
		return TL_URI.get();
	}
	
	/**
	 * 删除URI
	 */
	public static void removeUri() {
		TL_URI.remove();
	}
}
