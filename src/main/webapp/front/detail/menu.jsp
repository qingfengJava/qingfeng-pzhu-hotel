<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>攀大美味餐厅欢迎您——菜单首页</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="/front/detail/style/js/page_common.js"></script>
	<link href="/front/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/front/detail/style/css/index_1.css" />
	<link href="/front/detail/style/css/index.css" rel="stylesheet" type="text/css" />

	<style type="text/css">

		.active{
			background-color: #f6890c;
		}

		.page:hover{
			background-color: darkgrey;
		}
	</style>

</head>
<body style="text-align: center;background-color: goldenrod;">
<div class="nav" style="text-align: left;font-family: 楷体;">
	${foodType_name}
	<span style="float: right;padding-right: 20px">
		<c:if test="${loginUser != null}">
			<div style="position: relative;top: -15px;">
				<span>
					欢迎：<span style="color: red">${loginUser.nickName}</span>  光临攀大美味餐厅
				</span>
				<span>
					&nbsp;&nbsp;&nbsp;累计成功登录次数：<span style="color: red">5</span>次
				</span>
				<br>
				<span style="position: relative;top: -70px;">
					当前就餐人数：<span style="color: red">5</span>人&nbsp;&nbsp;
					时间：${day}
					<a href="/index.jsp" style="border: 1px solid darkgrey;border-radius: 10px;padding: 6px 5px;background-color: darkgrey;font-size: 15px;margin-left: 20px;">退出登录</a>
				</span>
			</div>
		</c:if>
		<c:if test="${loginUser == null}">
			<div style="position: relative;top: -15px;">
				<span>
					亲：<span style="color: red">登录才能点餐哟！！！</span>
				</span>
				<span style="position: relative;top: 0px;">
					时间：${day}
				</span>
			</div>
		</c:if>

	</span>
</div>
<div id="all">
	<div id="menu">
		<!-- 显示菜品的div -->
		<div id="top">
			<ul>
				<!-- 循环列出餐品 -->
				<c:forEach items="${pb.list}" var="food">
					<li>
						<a href="/front?method=findFoodById&foodId=${food.foodId}">
						<dl>
							<dt>
								<img src="${food.foodImage}" width="214px" height="145px" />
							</dt>
							<dd class="f1">
								${food.foodName}
							</dd>
							<dd class="f2">
								<span>原价：${food.foodPrice}</span> <span style="color:red;font-size: 15px;">会员价：${food.foodMprice}</span>
							</dd>
						</dl>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<!-- 底部分页导航条div -->
		<div id="foot"></div>

		<div style="margin-top: 40px;">
			<ul style="list-style-type: none;">
				<%-- 上一页边界逻辑判断 --%>
				<c:if test="${pb.currentPage <= 1}">
					<li style="float: left;"><a href="javascript:void(0)" style="border: 1px solid black;padding: 10px 10px;font-size: 15px;">上一页</a></li>
				</c:if>
				<c:if test="${pb.currentPage != 1}">
					<li style="float: left;"><a class="page" href="/front?method=findByPage&currentPage=${pb.currentPage - 1}&rows=8&typeId=${typeNum}" style="border: 1px solid black;padding: 10px 10px;font-size: 15px;">上一页</a></li>
				</c:if>


				<c:forEach begin="1" end="${pb.totalPage}" var="i">
					<%-- 如果页码和i相等，就要显示激活状态 --%>
					<c:if test="${pb.currentPage == i}">
						<li style="float: left"><a class="active page" href="/front?method=findByPage&currentPage=${i}&rows=8&typeId=${typeNum}"
												   style="border: 1px solid black;padding: 10px 15px;font-size: 15px;">${i}</a></li>
					</c:if>
					<c:if test="${pb.currentPage != i}">
						<li style="float: left"><a class="page" href="/front?method=findByPage&currentPage=${i}&rows=8&typeId=${typeNum}"
												   style="border: 1px solid black;padding: 10px 15px;font-size: 15px;">${i}</a></li>
					</c:if>

				</c:forEach>

				<%-- 下一页边界逻辑判断 --%>
				<c:if test="${pb.currentPage >= pb.totalPage}">
					<li style="float: left;"><a href="javascript:void(0)" style="border: 1px solid black;padding: 10px 10px;font-size: 15px;">下一页</a></li>
				</c:if>
				<c:if test="${pb.currentPage != pb.totalPage}">
					<li style="float: left;"><a class="page" href="/front?method=findByPage&currentPage=${pb.currentPage+1}&rows=8&typeId=${typeNum}" style="border: 1px solid black;padding: 10px 10px;font-size: 15px;">下一页</a></li>
				</c:if>
				<li style="float: left"><span style="padding-left: 20px;font-size: 20px">
					共<span style="font-weight: 700;font-size: 20px;color: black;">${pb.totalCount}</span>条数据
					第<span style="font-weight: 700;font-size: 20px;color: black;">${pb.totalPage}</span>页</span></li>
			</ul>
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
					<a href="/front?method=genernateOrder">我的餐单</a>
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
