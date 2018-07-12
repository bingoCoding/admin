/**
 * 
 */
package com.bingo.admin.commons.log.access;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import com.bingo.admin.commons.constant.CommonValue;
import com.bingo.admin.commons.constant.LogTopicConstant;
import com.bingo.admin.commons.log.anotation.ILogTracer;
import com.bingo.admin.utils.StringUtil;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.AccessLogValve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * 访问日志处理
 */
@Component
@ConditionalOnClass(AccessLogValve.class)
class AccessLogValueHandler extends AccessLogValve {
	
	/** 用于过滤排除URL */
	@Resource
	private CommonValue commonValue;
	
	/** 日志跟踪 */
	@Resource
	private ILogTracer logTracer;

	@Override
    public void log(CharArrayWriter message) {
		logTracer.trace(LogTopicConstant.TOPIC_ACCESS_LOG, message.toString());
    }
	
	@Override
    public void log(Request request, Response response, long time) {
		
		// 不需要关注的请求不打印日志
		if (commonValue.isFilterExclusiveUri(request.getRequestURI())) {
			return;
		}
		
		super.log(request, response, time);
	}
	
	/**
     * 处理URL查询参数
     */
    protected static class QueryElement implements AccessLogElement {
        @Override
        public void addElement(CharArrayWriter buf, Date date, Request request, Response response, long time) {
            String query = null;
            if (request != null) {
                query = request.getQueryString();
            }
            if (StringUtil.isNotEmpty(query)) {
                buf.append(query);
            } else {
            	buf.append('-');
            }
        }
    }
    
    /**
     * 处理UID参数
     */
    protected static class UidElement implements AccessLogElement {
        @Override
        public void addElement(CharArrayWriter buf, Date date, Request request, Response response, long time) {
            
        	String uid = null;
            if (request != null) {
            	uid = request.getParameter("uid");
            }
            if (StringUtil.isNotEmpty(uid)) {
                buf.append(uid);
            } else {
            	buf.append("-1");
            }
        }
    }
    
    /**
     * 处理APP版本参数
     */
    protected static class VerElement implements AccessLogElement {
        @Override
        public void addElement(CharArrayWriter buf, Date date, Request request, Response response, long time) {
            
        	String ver = null;
            if (request != null) {
            	ver = request.getParameter("ver");
            }
            if (StringUtil.isNotEmpty(ver)) {
                buf.append(ver);
            } else {
            	buf.append("1.1.1.1");
            }
        }
    }
    
    /**
     * 处理APP设备类型参数
     */
    protected static class DeviceTypeElement implements AccessLogElement {
        @Override
        public void addElement(CharArrayWriter buf, Date date, Request request, Response response, long time) {
            
        	String sys = null;
            if (request != null) {
            	sys = request.getParameter("sys");
            }
            if (StringUtil.isNotEmpty(sys)) {
                buf.append(sys);
            } else {
            	buf.append("sys");
            }
        }
    }
    
    /**
     * 处理POST数据
     */
    protected static class PostElement implements AccessLogElement {
		@Override
		public void addElement(CharArrayWriter buf, Date date, Request request, Response response, long time) {
			
			if (request == null || request.getMethod().toLowerCase().equals("get") 
					|| request.getParameterMap().isEmpty()) {
				buf.append("-");
				return;
			}
			
			handlerCommonParam(buf, request);
			
			// handlerPartParam(buf, request);
		}
		
		private void handlerCommonParam(CharArrayWriter buf, Request request) {
			Map<String, String[]> mapData = request.getParameterMap();
			String query = request.getQueryString() == null ? "" : request.getQueryString();
			if (!query.equals("")) {
				mapData = new HashMap<>(request.getParameterMap());
				for (String s : query.split("&")) {
					mapData.remove(s.split("=")[0]);
				}
			}
			
			int count = mapData.size(), outterIndex = 0;
			for (Map.Entry<String, String[]> entry : mapData.entrySet()) {
				 buf.append(entry.getKey()).append("=");
				 // 处理参数值，如果有多个用 $ 分割
				 int length = entry.getValue().length, innerIndex = 0;
				 for (String data : entry.getValue()) {
					 buf.append(data);
					 if (++innerIndex < length) {
						 buf.append("$");
					 }
				 }
				 // 处理参数间隔
				 if (++outterIndex < count) {
					 buf.append("&");
				 }
			}
		}
		
		@SuppressWarnings("unused")
		private void handlerPartParam(CharArrayWriter buf, Request request) {
			
			if (StringUtil.isEmpty(request.getContentType()) 
					|| !request.getContentType().split(";")[0].equals("multipart/form-data")) {
				return;
			}
			
			try {
				Collection<Part> parts = request.getParts();
				if (parts.isEmpty()) {
					return;
				}
				buf.append("||");
				int count = parts.size(), index = 0;
				for (Part part : parts) {
					buf.append((String) part.getName()).append("=").append(part.getSubmittedFileName());
					if (++index < count) {
						buf.append("&");
					}
				}
			} catch (IllegalStateException | IOException | ServletException e) {
			}
		}
    }
	
	/**
     * 创建日志解析元素
     */
    @Override
    protected AccessLogElement createAccessLogElement(char pattern) {
    	switch (pattern) {
    	case 'q':
            return new QueryElement();
    	case 'Q':
    		return new PostElement();
    	case 'z':
    		return new UidElement();
    	case 'Z':
    		return new VerElement();
    	case 'x':
    		return new DeviceTypeElement();
        default:
        	return super.createAccessLogElement(pattern);
    	}
    }
}
