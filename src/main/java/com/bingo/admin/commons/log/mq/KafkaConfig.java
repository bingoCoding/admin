/**
 * 
 */
package com.bingo.admin.commons.log.mq;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Kafka配置类
 * 
 */
@Configuration
@ConditionalOnProperty(prefix = "app.log.trace", name = "enable", matchIfMissing = false)
public class KafkaConfig {

	/** 服务地址 */
	@Value("${app.log.trace.server}")
	private String server;
	
	@Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put("producer.type", "async");	// 貌似已废弃
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 200);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5000);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 3000);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }
	
	@Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
	
	@Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
		KafkaTemplate<String, String> template = new KafkaTemplate<String, String>(producerFactory());
		template.setProducerListener(new NonLoggingProducerListener<String, String>());
        return template;
    }
}
