package com.qingfeng.controller;

import com.qingfeng.constant.MessageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 * @author 清风学Java
 * @date 2021/11/26
 * @version 1.0.0
 */
public class BaseServlet extends HttpServlet {

    /**
     * 脱离doGet和doPost方法，所求请求都要找service方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //获取参数method
            String methodName = req.getParameter(MessageConstant.PARAM_METHOD);

            //代码优化：请求参数method的值，同时也是处理业务的方法名
            //          如：delete
            //如果能够得到处理业务方法所在的类的class对象，就可以通过反射调用到业务层的方法
            //获取要调用方法的对象
            Class clazz = this.getClass();
            //获取方法
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //反射调用方法。谁处理业务方法，谁调用，谁处理
            //this：谁接收请求，谁调用这个方法，谁处理这个业务，谁就是this。
            Object obj = method.invoke(this, req, resp);
            //obj其实就是一个字符串，解决代码冗余问题，统一返回请求路径的字符串进行处理
            if (obj != null && obj instanceof String){
                //给出响应
                /*
                1、请求转发  forward:/url
                2、重定向   redirect:
                3、直接响应字符串
                 */
                String result = (String) obj;
                //截取我们要转发的url，只有匹配才能进行请求转发或者重定向
                String url = result.substring(result.indexOf(MessageConstant.TAG)+1);
                if (result.startsWith(MessageConstant.PREFIX_FORWAED)){
                    //匹配是请求转发
                    req.getRequestDispatcher(url).forward(req,resp);
                }else if (result.startsWith(MessageConstant.PREFIX_REDIRECT)){
                    //匹配是重定向
                    resp.sendRedirect(url);
                }else{
                    //都不是，响应错误信息
                    resp.getWriter().write(result);
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果上面的result不是字符串，那么就说明出现了异常
        //一但出现异常，统一处理
        resp.getWriter().write(MessageConstant.COMMON_ERROR_MESSAGE);
    }
}
