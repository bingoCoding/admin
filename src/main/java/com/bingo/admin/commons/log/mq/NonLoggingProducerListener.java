/**
 * 
 */
package com.bingo.admin.commons.log.mq;

import org.springframework.kafka.support.LoggingProducerListener;

/**
 * 覆盖父类方法，错误时不打印日志
 * 
 * @author 	alanwei
 * @since	2017-01-13
 */
public class NonLoggingProducerListener<K, V> extends LoggingProducerListener<K, V> {

	/**
	 * 不打印异常
	 * 
	 * @see org.springframework.kafka.support.LoggingProducerListener#onError(String, Integer, Object, Object, Exception)
	 */
	@Override
	public void onError(String topic, Integer partition, K key, V value, Exception exception) {
		
	}
}
