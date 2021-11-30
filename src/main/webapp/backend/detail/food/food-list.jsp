<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>攀大餐馆平台</title>
	
	<a href="${pageContext.request.contextPath}"></a>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/backend/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="/backend/detail/style/js/page_common.js"></script>
<link href="/backend/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/backend/detail/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="/backend/detail/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="/food" method="post">
			<input type="hidden" name="method" value="search">
			<%-- 判断域对象中的值。做数据回显 --%>
			<c:if test="${foodTypeName != null}">
				菜系名称：&nbsp;<input type="text" name="foodTypeName" value="${foodTypeName}" title="请输入菜系名称" placeholder="请输入菜系名称...">
			</c:if>
			<c:if test="${foodTypeName == null}">
				菜系名称：&nbsp;<input type="text" name="foodTypeName" title="请输入菜系名称" placeholder="请输入菜系名称...">
			</c:if>
			<c:if test="${foodName != null}">
				菜品名称：&nbsp;<input type="text" name="foodName" value="${foodName}" title="请输入菜品名称" placeholder="请输入菜品名称...">
			</c:if>
			<c:if test="${foodName == null}">
				菜品名称：&nbsp;<input type="text" name="foodName" title="请输入菜品名称" placeholder="请输入菜品名称...">
			</c:if>
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>序号</td>
				<td>菜品编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>图片</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>描述信息</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData" align="center">
			<%-- 遍历域对象中的foods集合。在页面渲染数据，varStatus="status"循环的状态。count是循环的次数 --%>
			<c:forEach items="${foods}" var="food" varStatus="status">
				<tr class="TableDetail1">
					<td>${status.count}</td>
					<td>${food.foodId}</td>
					<td>${food.foodName}</td>
					<td>${food.foodType.typeName}</td>
					<td><img src="${food.foodImage}" style="width: 120px"></td>
					<td>${food.foodPrice}</td>
					<td>${food.foodMprice}</td>
					<td>${food.foodDesc}</td>
					<td>
						<%-- 更新数据，要将数据的Id，一起传过去 --%>
						<a href="/food?method=toSaveUI&foodId=${food.foodId}" class="FunctionButton">更新</a>
						<a href="/food?method=deleteFood&foodId=${food.foodId}" onClick="return delConfirm();"class="FunctionButton">删除</a>
					</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="/backend/detail/food/food-save.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
