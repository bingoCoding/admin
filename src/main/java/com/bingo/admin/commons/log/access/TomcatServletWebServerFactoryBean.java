package com.bingo.admin.commons.log.access;

import com.bingo.admin.commons.constant.CommonValue;
import org.apache.catalina.valves.AccessLogValve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
@ConditionalOnClass(AccessLogValve.class)
@ConditionalOnProperty(prefix = "app.log.trace", name = "enable", matchIfMissing = false)
public class TomcatServletWebServerFactoryBean extends ServletWebServerFactoryCustomizer {

    public TomcatServletWebServerFactoryBean(ServerProperties serverProperties) {
        super(serverProperties);
    }

    @Resource
    private CommonValue commonValue;

    @Resource
    private AccessLogValueHandler accessLogValve;

    /**
     * 增加自己的日志处理
     *
     */
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        if(factory instanceof TomcatServletWebServerFactory){
            StringBuilder sb = new StringBuilder(1000);
            sb.append("{");
            sb.append("\"").append("uid").append("\":").append("%z").append(",");
            sb.append("\"").append("ver").append("\":\"").append("%Z").append("\",");
            sb.append("\"").append("sys").append("\":\"").append("%x").append("\",");
            sb.append("\"").append("appName").append("\":\"").append(commonValue.getAppName()).append("\",");
            sb.append("\"").append("localIp").append("\":\"").append("%A").append("\",");
            sb.append("\"").append("localPort").append("\":\"").append("%p").append("\",");
            sb.append("\"").append("realIp").append("\":\"").append("%{X-Real-IP}i").append("\",");
            sb.append("\"").append("forwardedFor").append("\":\"").append("%{X-Forwarded-For}i").append("\",");
            sb.append("\"").append("referer").append("\":\"").append("%{Referer}i").append("\",");
            sb.append("\"").append("reomoteIp").append("\":\"").append("%h").append("\",");
            sb.append("\"").append("accessTime").append("\":\"").append("%{yyyy-MM-dd HH:mm:ss.SSS}t").append("\",");
            sb.append("\"").append("method").append("\":\"").append("%m").append("\",");
            sb.append("\"").append("url").append("\":\"").append("%U").append("\",");
            sb.append("\"").append("query").append("\":\"").append("%q").append("\",");
            sb.append("\"").append("post").append("\":\"").append("%Q").append("\",");
            sb.append("\"").append("protocol").append("\":\"").append("%H").append("\",");
            sb.append("\"").append("status").append("\":\"").append("%s").append("\",");
            sb.append("\"").append("processTime").append("\":\"").append("%T").append("\",");
            sb.append("\"").append("autoAgent").append("\":\"").append("%{User-Agent}i").append("\"");
            sb.append("}");
            accessLogValve.setPattern(sb.toString());
            ((TomcatServletWebServerFactory) factory).setContextValves(Arrays.asList(accessLogValve));
        }
        super.customize(factory);
    }
}
