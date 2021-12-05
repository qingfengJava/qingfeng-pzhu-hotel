package com.qingfeng.filters;

import com.qingfeng.utils.GetDayForWeek;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义拦截器，设置请求编码，设置响应编码
 *
 * @author 清风学Java
 * @date 2021/11/24
 * @version 1.0.0
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        //对HTML，css，js放行，对Servlet加上编码
        if (!uri.endsWith("html") && !uri.endsWith("css") && !uri.endsWith("js") && !uri.endsWith("jpeg")){
            //不是HTML结尾，就拦截
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
        }

        //将年月日，星期几存入session域中，并要保证每跳转一个界面，就要重新存入，保证时间实时刷新
        req.getSession().setAttribute("day", GetDayForWeek.getDateDayForWeek());

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
