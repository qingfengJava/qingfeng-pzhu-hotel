<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style/css/index.css" />
	<link rel="stylesheet" type="text/css" href="style/css/index_1.css" />
	<script type="text/javascript">
		/** // 删除菜品项
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}
		*/
		// 下单
		function genernateOrder() {
			window.location.href = "/front?method=genernateOrder";
		}
	</script>
</head>

<body style="text-align: center;background-color: goldenrod;">
<div class="nav" style="text-align: left;font-family: 楷体;">
	我的餐单
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
				 		<td align="center" width="20%">操作</td>
				 	</tr>
					<c:forEach items="${cartList}" var="cartItem">
						<tr height="60">
						<td align="center" width="20%">${cartItem.food.foodName}</td>
						<td align="center" width="20%">￥${cartItem.price}</td>
						<td align="center" width="20%">
							<input type="text" value="${cartItem.num}" size="3" lang="3"/>
						</td>
						<td align="center" width="20%">${cartItem.foodTotalPrice}</td>
						<td align="center" width="20%">
							<input type="button" value="删除" class="btn_next" lang="3" onclick="removeSorder(this)" />
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp;${totalPrice}</span>
							<label id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="button" value="下单" class="btn_next" onclick="genernateOrder()" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">

			<div id="dish_2">
				<ul>
					<c:if test="${typeNum == 0}">
						<li style="background-color: whitesmoke;">
							<a href="/front?method=findByPage" style="color:goldenrod;font-weight: 700;">全部菜品</a>
						</li>
					</c:if>
					<c:if test="${typeNum != 0}">
						<li>
							<a href="/front?method=findByPage">全部菜品</a>
						</li>
					</c:if>

					<c:forEach items="${sessionScope.foodTypes}" var="type">
						<c:if test="${typeNum == type.typeId}">
							<li style="background-color: whitesmoke;">
								<a href="/front?method=findByPage&typeId=${type.typeId}" style="color:goldenrod;font-weight: 700;">${type.typeName}</a>
							</li>
						</c:if>
						<c:if test="${typeNum != type.typeId}">
							<li>
								<a href="/front?method=findByPage&typeId=${type.typeId}">${type.typeName}</a>
							</li>
						</c:if>
					</c:forEach>
					<li><a href=""></a></li>
					<li>
						<a href="/front/detail/clientCart.jsp">我的餐单</a>
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
