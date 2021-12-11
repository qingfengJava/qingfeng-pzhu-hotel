package com.qingfeng.filters;

import com.qingfeng.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截未登录的用户
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/11
 */
@WebFilter("/backend/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //只要后台管理员没有登录，就拦截
        User user = (User) req.getSession().getAttribute("adminUser");
        if (user == null) {
            //说明用户没有登录，去登录界面
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            //添加return，防止重复提交请求
            return;
        }

        chain.doFilter(request, response);
    }
}
