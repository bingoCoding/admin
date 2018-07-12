package com.bingo.admin.commons.exception;

import com.bingo.admin.commons.result.R;
import com.bingo.admin.utils.AnnotationUtil;
import com.bingo.admin.utils.IpUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

@Component
public class ExceptionService {

    /**
     * 检测调用是否是返回JSON
     *
     * @param handlerMethod		调用方法
     */
    public boolean isReturnJSon(HandlerMethod handlerMethod) {

        // 参数检测
        if (handlerMethod == null || handlerMethod.getMethod() == null) {
            return false;
        }

        // 调用方法
        Method method = handlerMethod.getMethod();

        // 检查方法的注解
        ResponseBody responseBody = AnnotationUtil.findAnnotation(method, ResponseBody.class);
        if (responseBody != null) {
            return true;
        }

        // 检查类的注解
        RestController restController = AnnotationUtil.findAnnotation(method.getDeclaringClass(), RestController.class);
        if (restController != null) {
            return true;
        }

        return false;
    }
    /**
     * 获取响应状态
     *
     * @param handlerMethod		调用方法
     */
    public ResponseStatus getResponseStatus(HandlerMethod handlerMethod) {

        // 参数检测
        if (handlerMethod == null || handlerMethod.getMethod() == null) {
            return null;
        }

        // 检查方法上的状态码注解
        Method method = handlerMethod.getMethod();
        ResponseStatus responseStatus = AnnotationUtil.findAnnotation(method, ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus;
        }

        // 检查类上的状态码注解
        responseStatus = AnnotationUtil.findAnnotation(method.getDeclaringClass(), ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus;
        }

        return null;
    }
    /**
     * 异常处理，根据实际逐步完善
     *
     * @param exception		异常信息
     */
    public R handleException(Exception exception) {

        // 其他后续补全

        // 记录异常信息（找到出现错误的方法，如果未执行到业务代码则使用URI作为方法名）
        String method = ThreadCache.getUri();
        boolean appendCommonExection = false;
        for (StackTraceElement ele : exception.getStackTrace()) {
            if (!ele.getClassName().startsWith("com.bingo")) {
                continue;
            }
            // 先追加第一个公用库的异常，用于追踪实际报错的地方
            if (!appendCommonExection) {
                String cmnMethon = getAppMethod(ele, 10, 15);
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(cmnMethon)) {
                    method = String.join(" || ", ThreadCache.getUri(), cmnMethon);
                    break;
                }
            }
//            String _method = getAppMethod(ele, 10, 15);
//            if (org.apache.commons.lang3.StringUtils.isNotEmpty(_method)) {
//                method = String.join(" || ", method, _method);
//                break;
//            }
        }

        System.out.println(method);

        // 异常信息增加 APP名称及IP地址
        StringBuilder sb = new StringBuilder();
        sb.append("appName:").append("admin").append(";");
        sb.append("ipAddr:").append(IpUtil.getLocalIp());
//        exceptionTracer.traceUnKnownException(exception.getMessage(), method, sb.toString());

        System.err.println("exception ==="+sb.toString());
        return R.error(exception.getMessage());
    }

    /**
     * 获取应用异常所在的方法
     *
     * @param ele			异常栈
     */
    public String getAppMethod(StackTraceElement ele, int start, int end) {
        try {
            System.out.println("StackTraceElement==="+ele.getClassName());
            String app = ele.getClassName().substring(start, end);
            if (app.equals("admin")) {
                StringBuilder sb = new StringBuilder(100);
                sb.append(ele.getClassName()).append(".");
                sb.append(ele.getMethodName());
                sb.append("(").append(ele.getFileName()).append(":").append(ele.getLineNumber()).append(")");
                return sb.toString();
            }
        } catch (Exception e) {
            // 以防万一
        }
        return "";
    }
}
