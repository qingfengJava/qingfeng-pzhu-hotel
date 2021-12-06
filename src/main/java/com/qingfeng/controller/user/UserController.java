package com.qingfeng.controller.user;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.User;
import com.qingfeng.service.UserService;
import com.qingfeng.utils.GetDayForWeek;
import com.qingfeng.utils.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //验证码
        String code = request.getParameter("code");
        //获取用户是否选择记住密码（免登陆）
        String check = request.getParameter("check");

        HttpSession session = request.getSession();

        //从session域中获取验证码
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        //获取之后就要清除，防止重复利用
        session.removeAttribute("checkCode");

        //先判断验证码是否输入正确
        if (code.equalsIgnoreCase(checkCode)){
            ResultVO resultVO = userService.login(username, password);
            if (resultVO.getSuccess()){
                User loginUser = (User) resultVO.getData();
                //将年月日，星期几存入session域中，并要保证每跳转一个界面，就要重新存入，保证时间实时刷新
                session.setAttribute("day", GetDayForWeek.getDateDayForWeek());

                //免密登录判断——根据前台设置的值1来判断
                if (check != null && Integer.parseInt(check) == 1){
                    //登录成功，设置cookie的存活时间
                    //创建Cookie
                    Cookie cookie = new Cookie(loginUser.getUsername(),loginUser.getPassword());
                    //设置存活时间3天
                    cookie.setMaxAge(60 * 60 * 24 * 3);
                    //消除JSESSIONNID不一致问题
                    Cookie cookie2 = new Cookie("JSESSIONID", request.getSession().getId());

                    response.addCookie(cookie);
                    response.addCookie(cookie2);
                }

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
            }
        }else{
            //验证码错误，请求转发回本页面
            request.setAttribute("login_msg","验证码输入错误！");
        }
        return MessageConstant.PREFIX_FORWAED+"/index.jsp";
    }

    /**
     * 用户注册  只能注册普通用户
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String register(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取前端用户传递过来的参数
            String username = request.getParameter("username");
            String oldPassword = request.getParameter("password");
            String nickName = request.getParameter("nickName");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");

            //获取验证码
            String code = request.getParameter("code");

            HttpSession session = request.getSession();

            //从session域中获取验证码
            String checkCode = (String) request.getSession().getAttribute("checkCode");
            //获取之后就要清除，防止重复利用
            session.removeAttribute("checkCode");

            //先判断验证码是否输入正确
            if (code.equalsIgnoreCase(checkCode)){
                //使用md5加密技术对密码进行加密
                String password = Md5Utils.md5(oldPassword);
                //创建一个用户对象，将用户数据进行保存
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setNickName(nickName);
                user.setPhone(phone);
                user.setGender(Integer.parseInt(gender));

                //设置默认信息
                //默认普通用户
                user.setIsAdmin(0);
                //默认激活
                user.setUserStatus(1);
                user.setUserCreateTime(new Date());
                user.setUserUpdateTime(new Date());
                //默认没有删除
                user.setIsDelete(0);
                //默认不是会员
                user.setIsMember(0);
                //暂时默认账上5000元
                user.setBalance(5000.00);

                //调用业务层注册用户的方法
                int index = userService.register(user);
                //根据返回的值来判断用户是否注册成功
                if(index == 1){
                    //注册成功，回到登录界面
                    request.setAttribute("login_msg","恭喜你，注册成功，马上登录吧！");
                }else {
                    //注册失败，请求转发回本页面
                    request.setAttribute("login_msg","用户已存在，注册失败！");
                }
            }else{
                //验证码错误，请求转发回本页面
                request.setAttribute("login_msg","验证码输入错误！");
            }
            //最终返回本页面
            return MessageConstant.PREFIX_FORWAED+"/index.jsp";
        } catch (NumberFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * 用户更新找回密码
     * @param request
     * @param response
     * @return
     */
    public String update(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取前端传过来的数据
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String code = request.getParameter("code");

            HttpSession session = request.getSession();

            //从session域中获取验证码
            String checkCode = (String) request.getSession().getAttribute("checkCode");
            //获取之后就要清除，防止重复利用
            session.removeAttribute("checkCode");

            //先判断验证码是否输入正确
            if (code.equalsIgnoreCase(checkCode)){
                //验证码正确，先判断用户名是否存在
                User user = userService.findByUsername(username);
                if (user != null){
                    //说明用户存在，可以修改/找回密码
                    String newPassword = Md5Utils.md5(password);
                    userService.updateByUserName(username,newPassword);

                    //更新/找回密码成功，回到登录界面
                    request.setAttribute("login_msg","恭喜你，密码更新/找回成功，马上登录吧！");
                }else{
                    //说明用户不存在，不能修改或找回密码
                    request.setAttribute("login_msg","用户不存在，不能修改/找回密码！");
                }
            }else {
                //验证码错误
                request.setAttribute("login_msg","验证错误，请重新输入！");
            }
            //最终返回本页面
            return MessageConstant.PREFIX_FORWAED+"/back.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
