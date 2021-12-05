package com.qingfeng.controller.code;

import com.qingfeng.utils.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 生成验证码的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
@WebServlet("/codeServlet")
public class CodeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("utf-8");

        //1、生成4位随机数
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //2、将得到的验证码保存到session作用域
        request.getSession().setAttribute("checkCode",code);
        //3、根据随机数生成图片 && 4、通过response响应图片 && 5、设置响应类型
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtils.outputImage(110,40,outputStream,code);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
