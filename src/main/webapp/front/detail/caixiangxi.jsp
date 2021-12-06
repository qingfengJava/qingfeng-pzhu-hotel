<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>攀大美味餐厅欢迎您！</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/page_common.js"></script>
<link href="style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/css/index_1.css" />
	<link href="style/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="style/css/dis_message.css" />
</head>
<body style="text-align: center;background-color: goldenrod;">
<div class="nav" style="text-align: left;font-family: 楷体;">
	菜品详细信息
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
		<!--左边菜品详细信息 -->
		<div id="menu1">
			<div class="menu2" style="text-align:center;">
				<img src="style/images/order_detials_bg.png" />
			</div>
			<div class="menu3">
				<div class="menu3_left">
					<img src="style/images/baizhuoxia.jpg"
						style="width:270px; height:290px;" />
				</div>
				<div class="menu3_right">
					<p>菜名:白灼虾</p>
					<p>价格:&nbsp;&nbsp;&yen;&nbsp;36.0</p>
					<p>简介:粤菜白灼虾，大件！</p>
				</div>
			</div>
			<div class="menu4">
				
				<a href="clientCart.jsp" style="background:url(style/images/img/order_left_corner_bg.png);">放入餐车</a>
				<a href="#" onclick="javascript:history.go(-1);" style="background:url(style/images/img/order_right_corner_bg.png);">返回</a>
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
						<a href="chuancai.jsp">湘菜</a>
					</li>

					<li>
						<a href="chuancai.jsp">东北菜</a>
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
