package com.qingfeng.controller.user;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.User;
import com.qingfeng.service.UserService;
import com.qingfeng.utils.GetDayForWeek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
@WebServlet("/user")
public class UserController extends BaseServlet {

    private UserService userService = (UserService) BeanFactory.getBean(BeanFactoryConstant.USER_USERSERVICE);

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //验证码
        String code = request.getParameter("code");

        HttpSession session = request.getSession();

        //从session域中获取验证码
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        //获取之后就要清除，防止重复利用
        session.removeAttribute("checkCode");

        //先判断验证码是否输入正确
        if (code.equals(checkCode)){
            ResultVO resultVO = userService.login(username, password);
            if (resultVO.getSuccess()){
                User loginUser = (User) resultVO.getData();
                //将年月日，星期几存入session域中，并要保证每跳转一个界面，就要重新存入，保证时间实时刷新
                session.setAttribute("day", GetDayForWeek.getDateDayForWeek());

                //登录成功，设置cookie的存活时间
                //创建Cookie
                Cookie cookie = new Cookie(loginUser.getUsername(),loginUser.getPassword());
                //设置存活时间3天
                cookie.setMaxAge(60 * 60 * 24 * 3);
                //消除JSESSIONNID不一致问题
                Cookie cookie2 = new Cookie("JSESSIONID", request.getSession().getId());

                response.addCookie(cookie);
                response.addCookie(cookie2);

                //用户登录要进行判断 是普通用户，还是管理员
                if (loginUser.getIsAdmin().intValue() == 0){
                    request.getSession().setAttribute("loginUser",loginUser);
                    //普通用户，直接去点餐页面
                    return MessageConstant.PREFIX_REDIRECT + "/front/index.jsp";
                }
                //管理员去后台
                request.getSession().setAttribute("adminUser",loginUser);
                return MessageConstant.PREFIX_REDIRECT+"/backend/index.jsp";
            }else{
                //验证码错误，请求转发回本页面
                request.setAttribute("login_msg",resultVO.getMessage());
                return MessageConstant.PREFIX_FORWAED+"/index.jsp";
            }
        }else{
            //验证码错误，请求转发回本页面
            request.setAttribute("login_msg","验证码输入错误！");
            return MessageConstant.PREFIX_FORWAED+"/index.jsp";
        }


    }
}
