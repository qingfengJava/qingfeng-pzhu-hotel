<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>攀大美味餐厅</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/backend/detail/style/js/page_common.js"></script>
<link href="/backend/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/backend/detail/style/css/index_1.css" />

	<style type="text/css">
		.active{
			background-color: #1484f6;
		}

		.page:hover{
			background-color: darkgrey;
		}
	</style>

	<script type="text/javascript">
			/**
			 * 删除前的确认提示
			 */
			function deleteById(id,currentPage){

				if(confirm("Are you sure you want to delete food information?\r\n您确定要删除id为"+id+"的菜品信息吗？")){
					location.href = "/food?method=deleteFood&foodId="+id+"&currentPage="+currentPage;
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
			<c:forEach items="${pb.list}" var="food" varStatus="status">
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
						<a href="/food?method=toSaveUI&foodId=${food.foodId}&currentPage=${pb.currentPage}" class="FunctionButton">更新</a>
						<a href="javascript:deleteById(${food.foodId},${pb.currentPage});" style="color: red" class="FunctionButton">删除</a>
					</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>

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
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="/backend/detail/food/food-save.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
