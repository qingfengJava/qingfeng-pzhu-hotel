<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>攀大美味餐厅</title>
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
			<img border="0" width="13" height="13" src="/backend/detail/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="/dinnertable?method=search" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="请输入餐桌名称" placeholder="请输入餐桌的名称...">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">

			<c:forEach items="${tables}" var="table">
				<tr class="TableDetail1" align="center">
					<td>${table.tableId}</td>
					<td>${table.tableName}</td>
					<td>${table.tableStatus == 0 ? "空闲":"已预定"}</td>
					<td>${table.reservationTimeStr}</td>
					<td>
						<div style="margin-left: 100px;">
							<a href="/dinnertable?method=update&tableStatus=${table.tableStatus}" class="FunctionButton">
								${table.tableStatus == 0 ? "预定":"退桌"}</a>
							<c:if test="${table.tableStatus == 0}">
								<a href="/wirelessplatform/board.html?method=delete&id=1" style="color: red" onClick="return delConfirm();"class="FunctionButton">删除</a>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="saveBoard.html">添加</a></div>
    </div> 
</div>
</body>
</html>
