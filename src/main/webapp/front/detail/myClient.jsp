<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/front-style/css/index.css" />
	<link rel="stylesheet" type="text/css" href="/front-style/css/index_1.css" />
</head>

<body style="text-align: center;background-color: goldenrod;">
<div class="nav" style="text-align: left;font-family: 楷体;">
	我的历史餐单
	<span style="float: right;padding-right: 20px">
		<c:if test="${loginUser != null}">
			<div style="position: relative;top: -15px;">
				<span>
					欢迎：<span style="color: red">${loginUser.nickName}</span>光临就餐
				</span>
				<span>
					&nbsp;&nbsp;&nbsp;累计成功登录次数：<span style="color: red">${loginUser.loginNum+1}</span>次
				</span>
				<br>
				<span style="position: relative;top: -70px;">
					当前就餐人数：<span style="color: red">${onlineCount}</span>人&nbsp;&nbsp;
					时间：${day}
					<a href="/user?method=quit" style="border: 1px solid darkgrey;border-radius: 10px;padding: 6px 5px;background-color: darkgrey;font-size: 15px;margin-left: 20px;">退出登录</a>
				</span>
			</div>
		</c:if>
		<c:if test="${loginUser == null}">
			<span>
				亲：<a href="/"><span style="color: red;font-size: 25px">登录才能点餐哟！！！</span></a>
			</span>
			<span style="position: relative;top: 0px;">
				时间：${day}
			</span>
		</c:if>
	</span>
</div>
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="left" width="140%">
					<tr height="40">
						<td align="center">订单编号</td>
						<td align="center">餐桌名</td>
						<td align="center">用户</td>
						<td align="center">菜品数量</td>
						<td align="center">下单日期</td>
						<td align="center">总金额</td>
						<td align="center">状态</td>
				 	</tr>
					<c:forEach items="${orderList}" var="list">
						<tr height="60">
							<td align="center">${list.orderId}</td>
							<td align="center">${list.tableName}</td>
							<td align="center">${list.userName}</td>
							<td align="center">${list.num}</td>
							<td align="center">${list.orderCreateDateStr}</td>
							<td align="center">￥${list.totalPrice}</td>
							<c:if test="${list.status == 0}">
								<td>未结账</td>
							</c:if>
							<c:if test="${list.status == 1}">
								<td>已结账</td>
							</c:if>
						</tr>
					</c:forEach>
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
					<li style="background-color: whitesmoke;">
						<a href="/front?method=myClient&userId=${loginUser.userId}" style="color:goldenrod;font-weight: 700;">我的历史餐单</a>
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
