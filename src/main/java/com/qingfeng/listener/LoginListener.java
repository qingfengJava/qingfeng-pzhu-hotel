package com.qingfeng.listener;
/**
 * @author 清风学Java
 * @date 2021/12/10
 * @version 1.0.0
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class LoginListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public LoginListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }
}
