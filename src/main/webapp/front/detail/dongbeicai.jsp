<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>攀大美味餐厅欢迎您！</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="style/js/jquery.js"></script>
    <script type="text/javascript" src="style/js/page_common.js"></script>
    <link href="style/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="style/css/index_1.css"/>
    <link href="style/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body style="text-align: center;background-color: goldenrod;">
<div class="nav" style="text-align: left;font-family: 楷体;">
    东北菜
    <span style="float: right;padding-right: 20px">
		<div style="position: relative;top: -15px;">
			<span>
				欢迎：<span style="color: red">${loginUser.nickName}</span>  光临攀大美味餐厅
			</span>
			<span>
				当前就餐人数：<span style="color: red">5</span>人
			</span>
			<br>
			<span style="position: relative;top: -70px;">
				时间：${day}
				<a href="#" style="border: 1px solid darkgrey;border-radius: 10px;padding: 6px 5px;background-color: darkgrey;font-size: 15px;margin-left: 100px;">退出登录</a>
			</span>
		</div>
	</span>
</div>
<div id="all">
    <div id="menu">
        <!-- 显示菜品的div -->
        <div id="top">
            <ul>
                <!-- 循环列出餐品 -->
                <li>
                    <dl>
                        <dt>
                            <a href="caixiangxi.jsp">
                                <img width="214px" height="145px" src="style/images/shaoe.jpg"/>
                            </a>
                        </dt>
                        <dd class="f1">
                            <a href="caixiangxi.jsp">烧鹅</a>
                        </dd>
                        <dd class="f2">
                            <a href="caixiangxi.jsp">&yen;68.0</a>
                        </dd>
                    </dl>
                </li>

                <li>
                    <dl>
                        <dt>
                            <a href="caixiangxi.jsp">
                                <img width="214px" height="145px" src="style/images/guotourou.jpg"/>
                            </a>
                        </dt>
                        <dd class="f1">
                            <a href="caixiangxi.jsp">锅头肉</a>
                        </dd>
                        <dd class="f2">
                            <a href="caixiangxi.jsp">&yen;23.0</a>
                        </dd>
                    </dl>
                </li>

                <li>
                    <dl>
                        <dt>
                            <a href="caixiangxi.jsp">
                                <img width="214px" height="145px" src="style/images/huotuibaicai.jpg"/>
                            </a>
                        </dt>
                        <dd class="f1">
                            <a href="caixiangxi.jsp">火腿白菜</a>
                        </dd>
                        <dd class="f2">
                            <a href="caixiangxi.jsp">&yen;23.0</a>
                        </dd>
                    </dl>
                </li>

                <li>
                    <dl>
                        <dt>
                            <a href="caixiangxi.jsp">
                                <img width="214px" height="145px" src="style/images/qingjiaojiding.jpg"/>
                            </a>
                        </dt>
                        <dd class="f1">
                            <a href="caixiangxi.jsp">青椒鸡丁</a>
                        </dd>
                        <dd class="f2">
                            <a href="caixiangxi.jsp">&yen;23.0</a>
                        </dd>
                    </dl>
                </li>

                <li>
                    <dl>
                        <dt>
                            <a href="caixiangxi.jsp">
                                <img width="214px" height="145px" src="style/images/xiangguorouwan.jpg"/>
                            </a>
                        </dt>
                        <dd class="f1">
                            <a href="caixiangxi.jsp">香锅肉丸</a>
                        </dd>
                        <dd class="f2">
                            <a href="caixiangxi.jsp">&yen;23.0</a>
                        </dd>
                    </dl>
                </li>

            </ul>
        </div>

        <!-- 底部分页导航条div -->
        <div id="foot"></div>
        <div style="margin-top: 40px">
            <ul style="list-style-type: none;">
                <%-- 上一页边界逻辑判断 --%>
                <c:if test="${pb.currentPage <= 1}">
                    <li style="float: left;"><a class="page" href="/food?method=search&currentPage=1&rows=5&foodName=${foodName}&foodTypeName=${foodTypeName}" style="border: 1px solid black;padding: 10px 10px;">上一页</a></li>
                </c:if>
                <c:if test="${pb.currentPage != 1}">
                    <li style="float: left;"><a class="page" href="/food?method=search&currentPage=${pb.currentPage - 1}&rows=5&foodName=${foodName}&foodTypeName=${foodTypeName}" style="border: 1px solid black;padding: 10px 10px;">上一页</a></li>
                </c:if>


                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <%-- 如果页码和i相等，就要显示激活状态 --%>
                    <c:if test="${pb.currentPage == i}">
                        <li style="float: left"><a class="active page" href="/food?method=search&currentPage=${i}&rows=5&foodName=${foodName}&foodTypeName=${foodTypeName}"
                                                   style="border: 1px solid black;padding: 10px 15px;">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li style="float: left"><a class="page" href="/food?method=search&currentPage=${i}&rows=5&foodName=${foodName}&foodTypeName=${foodTypeName}"
                                                   style="border: 1px solid black;padding: 10px 15px;">${i}</a></li>
                    </c:if>

                </c:forEach>

                <%-- 下一页边界逻辑判断 --%>
                <c:if test="${pb.currentPage >= pb.totalPage}">
                    <li style="float: left;"><a class="page" href="/food?method=search&currentPage=${pb.totalPage}&rows=5&foodName=${foodName}&foodTypeName=${foodTypeName}" style="border: 1px solid black;padding: 10px 10px;">下一页</a></li>
                </c:if>
                <c:if test="${pb.currentPage != pb.totalPage}">
                    <li style="float: left;"><a class="page" href="/food?method=search&currentPage=${pb.currentPage+1}&rows=5&foodName=${foodName}&foodTypeName=${foodTypeName}" style="border: 1px solid black;padding: 10px 10px;">下一页</a></li>
                </c:if>
                <li style="float: left"><span style="padding-left: 20px;font-size: 20px">共${pb.totalCount}条数据</span></li>
                <li><span style="padding-left: 20px;font-size: 20px">第${pb.totalPage}页</span></li>
            </ul>
        </div>
    </div>

    <!-- 右边菜系列表，菜品搜索框  -->
    <div id="dish_class">
        <div id="dish_2">
            <ul>
                <li>
                    <a href="/front/detail/menu.jsp">全部菜品</a>
                </li>

                <li>
                    <a href="menu.jsp">粤菜</a>
                </li>

                <li>
                    <a href="chuancai.jsp">川菜</a>
                </li>

                <li>
                    <a href="xiangcai.jsp">湘菜</a>
                </li>

                <li>
                    <a href="dongbeicai.jsp">东北菜</a>
                </li>
                <li><a href=""></a></li>
                <li style="background-color: whitesmoke;">
                    <a href="clientCart.jsp" style="color:goldenrod;font-weight: 700;">我的餐单</a>
                </li>
            </ul>
        </div>

    </div>
</div>
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
