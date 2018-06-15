package com.bingo.admin.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    /**
     * 检测是否为空串
     *
     * @param objects	字符串列表
     * @return	true：有一个为空船  false：都不是空串
     */
    public static boolean isEmpty(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;	// 不能为null
            }
            if (object.toString().trim().equals("")) {
                return true;	// 不能全是空串
            }
        }
        return false;
    }

    /**
     * 检测是否不是空串
     *
     * @param objects	字符串列表
     * @return	true：参数均不是空串  false：有一个为空串
     */
    public static boolean isNotEmpty(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;	// 不能为null
            }
            if (object.toString().trim().equals("")) {
                return false;	// 不能全是空串
            }
        }
        return true;
    }

    /**
     * 获取字符串的安全值，非null值
     *
     * @param object	对象值
     */
    public static String getSafeValue(Object object) {
        return getSafeValue(object, "");
    }
    /**
     * 获取字符串的安全值，非null值或传入的默认值
     *
     * @param object		对象值
     * @param defaultValue	默认值
     */
    public static String getSafeValue(Object object, String defaultValue) {
        // 参数检测
        if (StringUtil.isEmpty(object)) {
            if (StringUtils.isEmpty(defaultValue)) {
                return "";
            }
            return defaultValue;
        }
        return object.toString();
    }
}
