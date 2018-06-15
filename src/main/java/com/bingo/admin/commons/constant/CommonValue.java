package com.bingo.admin.commons.constant;


import com.bingo.admin.utils.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 公用常量
 *
 */
@Component("commonValue")
@ConfigurationProperties(prefix = "app.common")
public class CommonValue {
    /** 应用名称 */
    private String appName;

    /** 安全验证禁用 */
    private boolean authDisabled = false;

    /** 过滤器排除的后缀url */
    private String filterExclusiveSuffix;
    private Map<String, String> mapFilterExclusiveSuffix = new HashMap<>();

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return the authDisabled
     */
    public boolean isAuthDisabled() {
        return authDisabled;
    }

    /**
     * @param authDisabled the authDisabled to set
     */
    public void setAuthDisabled(boolean authDisabled) {
        this.authDisabled = authDisabled;
    }

    /**
     * @return the filterExclusiveSuffix
     */
    public Map<String, String> getFilterExclusiveSuffix() {
        return mapFilterExclusiveSuffix;
    }

    /**
     * @param filterExclusiveSuffix the filterExclusiveSuffix to set
     */
    public void setFilterExclusiveSuffix(String filterExclusiveSuffix) {
        this.filterExclusiveSuffix = filterExclusiveSuffix;

        // 未配置不做处理
        if (StringUtil.isEmpty(filterExclusiveSuffix)) {
            return;
        }

        // 将后缀拆分出来，后续方便使用
        String[] temp = this.filterExclusiveSuffix.split(",");
        for (String t : temp) {
            if (StringUtil.isNotEmpty(t)) {
                mapFilterExclusiveSuffix.put(t, "");
            }
        }
    }

    /**
     * 判断是否是过滤器排除的URL
     *
     * @param url	本次请求url
     */
    public boolean isFilterExclusiveUri(String url) {
        for (String key : mapFilterExclusiveSuffix.keySet()) {
            if (url.endsWith(key)) {
                return true;
            }
        }
        return false;
    }
}
