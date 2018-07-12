package com.bingo.admin.commons.exception;

import com.alibaba.druid.support.json.JSONUtils;
import com.bingo.admin.commons.log.anotation.ExceptionTracer;
import com.bingo.admin.commons.result.R;
import com.bingo.admin.utils.AnnotationUtil;
import com.bingo.admin.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 异常处理器
 */
@Component
public class ExceptionHandler extends ExceptionHandlerExceptionResolver {

    /** 日志 */
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    /** 默认错误页面 */
    private String errorPage="error/500";

    /** 异常跟踪 */
    @Resource
    private ExceptionTracer exceptionTracer;

    @Resource
    private ExceptionService exceptionService;


    /**
     * 处理异常
     *
     * Find an {@code @ExceptionHandler} method and invoke it to handle the raised exception.
     */
    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
                                                           HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {

        // 非JSON返回的异常处理，传统JSP、各种视图返回
        if (!exceptionService.isReturnJSon(handlerMethod)) {
            ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
            if (returnValue != null && StringUtils.isEmpty(returnValue.getViewName())) {
                returnValue.setViewName(errorPage);
            }
            return returnValue;
        }

        // 状态码返回的异常处理，标准化REST返回，暂不做太多处理
        ResponseStatus responseStatus = exceptionService.getResponseStatus(handlerMethod);
        if (responseStatus != null) {
            HttpStatus httpStatus = responseStatus.value();
            String reason = responseStatus.reason();
            if (!StringUtils.hasText(reason)) {
                response.setStatus(httpStatus.value());
            } else {
                try {
                    response.sendError(httpStatus.value(), reason);
                } catch (IOException e) {
                    LOG.error("ResponseStatus Handler Error", e);
                }
            }

            return super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
        }

        // 还有一种，目前应用很少，暂不做处理

        // JSON返回的异常处理，自定义JSON返回结果
        try {
            // 只处理内部定义异常
            R resp = exceptionService.handleException(exception);
            if (resp != null) {
                // 返回JSON数据
                response.setContentType("application/json;charset=utf-8");
                response.getOutputStream().write(JSONUtils.toJSONString(resp).getBytes());
            }
        } catch (IOException e) {
            LOG.error("ApiResponse Handler Error", e);
        }

        return new ModelAndView();
    }





    /**
     * 处理自定义异常
     *
     * @param exception		异常信息
     */
    private R handleParamErrorException(Exception exception) {

        // 其他
        return R.error();
    }
}
