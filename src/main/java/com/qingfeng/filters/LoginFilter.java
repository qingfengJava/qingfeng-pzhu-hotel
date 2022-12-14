package com.qingfeng.filters;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.User;
import com.qingfeng.service.UserService;
import com.qingfeng.utils.GetDayForWeek;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 使用Filter实现用户免登陆
 *
 * @author 清风学Java
 * @date 2021/12/1
 * @version 1.0.0
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST},urlPatterns = {"/index.jsp"})
public class LoginFilter implements Filter {

    private UserService userService = (UserService) BeanFactory.getBean(BeanFactoryConstant.USER_USERSERVICE);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //首次进行判断，cookie是否存在，获取cookie
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();

        String quit = (String) session.getAttribute("quit");
        //当quit是false时，说明用户点击退出，不让拦截器生效
        if (!"false".equals(quit)) {
            //进行判断用户是否存在
            if (cookies != null && cookies.length > 0) {

                for (Cookie cookie : cookies) {
                    //得到用户名
                    String name = cookie.getName();
                    //得到密码
                    String password = cookie.getValue();
                    //调用数据库查询用户是否存在
                    User user = userService.findByUsername(name);
                    if (user != null && user.getPassword().equals(password)) {
                        //将年月日，星期几存入session域中，并要保证每跳转一个界面，就要重新存入，保证时间实时刷新
                        session.setAttribute("day", GetDayForWeek.getDateDayForWeek());

                        //用户登录要进行判断 是普通用户，还是管理员
                        if (user.getIsAdmin().intValue() == 0) {
                            //普通用户，直接去点餐页面
                            //用户存在，将用户信息存储到session
                            session.setAttribute("loginUser", user);
                            userService.addLoginNum(user);
                            resp.sendRedirect(req.getContextPath() + "/front/index.jsp");
                            return;
                        } else {
                            //管理员去后台
                            //用户存在，将用户信息存储到session
                            session.setAttribute("adminUser", user);
                            userService.addLoginNum(user);
                            resp.sendRedirect(req.getContextPath() + "/backend/index.jsp");
                            return;
                        }
                    }
                }
                //用户不存在，说明是第一次，去登录界面
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                //添加return，防止重复提交请求
                return;

            } else {
                //用户不存在，说明是第一次，请求转发到登录页面
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }else{
            //用户不存在，说明是第一次，请求转发到登录页面
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
