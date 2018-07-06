package com.bingo.admin.commons.config;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 由于在http请求线程之外,openssessioninviewfilter不起作用，导致线程没有绑定session，这个类就是为了解决此问题。
 * 自己管理Hibernate Session的Runnable。
 * @author taojw
 */
public abstract class TxSessionRunnable implements Runnable {
    @Override
    public final void run(){
        SessionFactory sessionFactory = (SessionFactory)SpringContextHolder.getApplicationContext().getBean("sessionFactory");
        boolean participate = bindHibernateSessionToThread(sessionFactory);
        try{
            execute();
        }finally{
            closeHibernateSessionFromThread(participate, sessionFactory);
        }
    }
    public void execute(){

    }
    public static boolean bindHibernateSessionToThread(SessionFactory sessionFactory) {
        if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
            // Do not modify the Session: just set the participate flag.
            return true;
        } else {
            Session session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.MANUAL);
            SessionHolder sessionHolder = new SessionHolder(session);
            TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
        }
        return false;
    }
    public static void closeHibernateSessionFromThread(boolean participate, Object sessionFactory) {
        if (!participate) {
            SessionHolder sessionHolder = (SessionHolder)TransactionSynchronizationManager.unbindResource(sessionFactory);
            SessionFactoryUtils.closeSession(sessionHolder.getSession());
        }
    }

}