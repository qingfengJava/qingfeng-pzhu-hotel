<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>

    <style type="text/css">
        body {
            background-image: url('img/bg1.jpeg');
            background-repeat: no-repeat;
            background-position: center top;
            background-size: 100% 100%;
            background-attachment: fixed;
        }

        /* 设置底部超链接取消下划线 */
        .list-inline{
            list-style-type: none;
        }
        /* 设置每一个底部超链接的距离及浮动 */
        .list-inline li{
            padding: 20px;
            float: left;
        }
        .list-inline li a{
            font-weight: 500;
            font-size: 18px;
            text-decoration-line: none;
        }

        /* 设置鼠标经过超链接时的样式 */
        .list-inline li a:hover{
            font-weight: 700;
            font-size: 20px;
            text-decoration-line: underline;
        }

        /* 设置表格的宽度 */
        .row{
            width: 1100px;
        }
        /* 清除浮动 */
        .row:before,
        .row:after {
            content: "";
            display: table;
        }
        .row:after {
            clear: both;
        }
        .row {
            *zoom: 1;
        }

        /* 设置底部表格的宽度 */
        .footer-table{
            width: 100%;
            text-align: center;
        }

        /* 设置底部文字的描述 */
        .buttom{
            font-weight: 700;
            padding: 20px;
            margin-bottom: 20px;
        }
    </style>

</head>
<body>

<form action="${pageContext.request.contextPath}/user" method="post">
    <input type="hidden" name="method" value="login" />
    <table align="center"
           style="border: 2px solid darkgrey;border-radius: 10px;margin-top: 150px; background-color: lightgrey;"
           width="1000px" height="400px">
        <tr>
            <td colspan="2">
                <div style="text-align: center;font-family: '华文行楷';font-size: 60px;font-weight: bold;">
                    攀大美味餐厅欢迎您！
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <img src="/img/bg2.jpeg"/>
            </td>
            <td>
                <table align="center"
                       style="border: 2px solid darkgrey;border-radius: 10px; background-color: lightgrey;"
                       width="400px" height="300px">
                    <tr>
                        <td colspan="2" align="center" style="font-family: '华文行楷';font-size: 40px;color: deepskyblue">
                            用户登录
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div style="padding: 5px; border-radius: 8px;text-align: center;">
                                <strong style="font-size:15px;color: red">${login_msg}</strong>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="font-size: 20px;">用户名：</td>
                        <td align="center"><input type="text" name="username" id="user" placeholder="请输入用户名"
                                                  style="border-radius: 3px;width:220px;height:30px;font-size:20px;border: lightgrey solid"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="font-size: 20px;">密码：</td>
                        <td align="center"><input type="password" name="password" id="password" placeholder="请输入密码"
                                                  style="border-radius: 3px;width:220px;height:30px;font-size:20px;border: lightgrey solid"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="font-size: 20px;">验证码：</td>
                        <td align="center">
                            <input type="text" name="code" id="code" placeholder="验证码"
                                   style="border-radius: 3px;width:115px;height:30px;font-size:20px;border: lightgrey solid;vertical-align: middle"/>
                            <a href="javascript:refreshCode()">
                                <img src="${pageContext.request.contextPath}/codeServlet" title="看不清点击刷新" id="img_code"
                                     style="vertical-align: middle"/>
                            </a>
                            <script>
                                function refreshCode() {
                                    document.getElementById("img_code").src = "${pageContext.request.contextPath}/codeServlet?" + (new Date()).getTime();
                                }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div>
                            <span style="padding-left: 20PX">
                                <input type="checkbox" name="check"/> 记住密码
                            </span>
                                <span style="margin-left: 190px">
                                <a href="#">忘记密码</a>
                            </span>
                                <div style="text-align: right">
                                    <a href="#">无账号，前去注册</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="登录" style="width:80px;height: 40px;font-size: 20px">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>

<!-- footer -->
<table class="footer-table">
    <tr>
        <td align="center">
            <div class="row">
                <ul class="list-inline">
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">联系我们</a></li>
                    <li><a href="#">招贤纳士</a></li>
                    <li><a href="#">法律声明</a></li>
                    <li><a href="#">友情连接</a></li>
                    <li><a href="#">支付方式</a></li>
                    <li><a href="#">配送方式</a></li>
                    <li><a href="#">服务声明</a></li>
                    <li><a href="#">广告声明</a></li>
                </ul>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="buttom">
                <span>Copyriht &copy; 2021-2022 攀枝花学院&nbsp;&nbsp;&nbsp;&nbsp; 版权归属：Web应用开发专业团队</span>
            </div>
        </td>
    </tr>
</table>

</body>
</html>