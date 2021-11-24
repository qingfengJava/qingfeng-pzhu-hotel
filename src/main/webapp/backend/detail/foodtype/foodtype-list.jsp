<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>攀大点餐系统——菜系管理</title>

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
				<img border="0" width="13" height="13"
					src="/backend/detail/style/images/title_arrow.gif" /> 菜系列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>
	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="/foodType/search" method="post">
			<input type="hidden" name="method" value="search">
			&nbsp;&nbsp;菜系名称: <input type="text" name="keyword" placeholder="请输入菜系名称" title="请输入菜系名称" value="${keyword}">
			<input type="submit" value="搜索">
		</form>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>菜系编号</td>
					<td>菜系名称</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData" align="center">

				<c:forEach items="${foodTypes}" var="type">
					<tr>
						<td>${type.typeId}</td>
						<td>${type.typeName}</td>
						<td>
							<a href="/foodtype/findById?typeId=${type.typeId}" class="FunctionButton">更新</a>
							<a href="javascript:void(0)" class="FunctionButton" onclick="deleteById(${type.typeId});">删除</a>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
			<div class="FunctionButton">
				<a href="/backend/detail/foodtype/foodtype-save.jsp">添加</a>
			</div>
		</div>
	</div>
</body>

<script>
	function deleteById(id) {
		if (delConfirm(id)){
			//确认之后，执行删除操作，发送请求
			location.href = "/foodtype/delete?typeId="+id;
		}
	}
</script>

</html>
