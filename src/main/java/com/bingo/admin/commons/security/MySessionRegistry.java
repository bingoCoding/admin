package com.bingo.admin.commons.security;

import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;

/**
 * session管理
 * Created by Hong on 2016/11/22
 */
@Service("MySessionRegistry")
public class MySessionRegistry extends SessionRegistryImpl {

    @Override
    public void registerNewSession(String sessionId, Object principal) {
        super.registerNewSession(sessionId, principal);
    }

    @Override
    public void removeSessionInformation(String sessionId) {
        super.removeSessionInformation(sessionId);
    }

    @Override
    public void refreshLastRequest(String sessionId) {
        super.refreshLastRequest(sessionId);
    }
}
