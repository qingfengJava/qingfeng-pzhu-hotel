<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>攀大美味餐厅</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/backend-style/js/page_common.js"></script>
<link href="/backend-style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/backend-style/css/index_1.css" />
	<script>
		function callPay(orderId,totalPrice) {
			if (confirm("你确定要支付"+totalPrice+"元吗？")){
				location.href = "order?method=callPay&orderId="+orderId+"&totalPrice="+totalPrice;
			}
		}
	</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="/backend-style/images/title_arrow.gif" /> 餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>用户</td>
					<td>菜品数量</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData" align="center">

				<c:forEach items="${orderList}" var="list">
					<tr height="50">
						<td>${list.orderId}</td>
						<td>${list.tableName}</td>
						<td>${list.userName}</td>
						<td>${list.num}</td>
						<td>${list.orderCreateDateStr}</td>
						<td>￥${list.totalPrice}</td>
						<c:if test="${list.status == 0}">
							<td>未结账</td>
						</c:if>
						<c:if test="${list.status == 1}">
							<td>已结账</td>
						</c:if>
						<td>
							<a href="/order?method=orderDetail&orderListId=${list.orderId}" class="FunctionButton">详细</a>
							<c:if test="${list.status == 0}">
								<a href="javascript:callPay(${list.orderId},${list.totalPrice});" class="FunctionButton">结账</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
		</div>
	</div>
</body>
</html>
