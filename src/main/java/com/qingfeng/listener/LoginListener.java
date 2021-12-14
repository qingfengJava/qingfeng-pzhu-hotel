package com.qingfeng.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 清风学Java
 * @date 2021/12/10
 * @version 1.0.0
 */
@WebListener
public class LoginListener implements HttpSessionListener {

    /**
     * 当前服务会话开启时
     *
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        //当有用户登录时，触发监听器
        //通过httpSessionEvent来获取servletContext对象
        ServletContext ctx = session.getServletContext();
        //获取servletContext中的onlineCount的值，首先是不存在的
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");
        if (onlineCount == null) {
            //当不存在时，往onlineCount里面存值1
            onlineCount = -1;
        } else {
            //否则当存在时，onlineCount的值就+1
            onlineCount += 1;
        }
        //将改变的onlineCount值存入session中
        ctx.setAttribute("onlineCount", onlineCount);
    }

    /**
     * 当前服务会话结束时
     *
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext ctx = session.getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");
        if (onlineCount == null) {
            onlineCount = 0;
        } else {
            //当会话关闭时，onlineCount的值就-1
            onlineCount -= 1;
        }
        ctx.setAttribute("onlineCount", onlineCount);
    }
}
