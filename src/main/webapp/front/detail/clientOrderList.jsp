<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>攀大美味餐厅欢迎您！</title>
	<link rel="stylesheet" type="text/css" href="style/css/index.css" />
	<link rel="stylesheet" type="text/css" href="style/css/index_1.css"/>
	<script type="text/javascript">
		// 通知服务员结账
		function callPay(node) {
			var orderId = node.lang;
			window.location.href = "jiezhang.jsp";
		}
	</script>
</head>

<body style="text-align: center;background-color: goldenrod;">
<div class="nav" style="text-align: left;font-family: 楷体;">
	结账
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
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 	</tr>
					

					<tr height="60">
					 		<td align="center" width="20%">烤乳猪</td>
					 		<td align="center" width="20%">￥68.0</td>
					 		<td align="center" width="20%">1</td>
					 		<td align="center" width="20%">68.0</td>
				 		</tr>
				 	

					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;</span>
							<label
								id="counter" style="font-size:36px">68.0</label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="结账" class="btn_next" lang="" onclick="callPay(this)" />
						</td>
					</tr>
				</table>
			</div>
		</div>
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
