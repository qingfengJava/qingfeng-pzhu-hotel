<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	
<title>攀大餐馆平台</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/backend/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="/backend/detail/style/js/page_common.js"></script>
<link href="/backend/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/backend/detail/style/css/index_1.css" />
	<script type="text/javascript">

		window.onload = function (){
			//对提交做判断
			document.getElementById("form").onsubmit = function () {
				//获取各个输入框的对象
				let foodName = document.getElementById("foodName");
				let price = document.getElementById("price");
				let mprice = document.getElementById("mprice");
				let introduce = document.getElementById("introduce");

				if (foodName.value != "" && price.value != "" && mprice.value != "" && introduce.text != ""){
					if (mprice.value < price.value){
						return confirm("Are you sure you want to update food information?\r\n你确定要修改菜品信息吗？");
					}else {
						alert("修改会员定价不符合常理！");
						return false;
					}
				}else {
					alert("修改信息不能为空！");
					return false;
				}


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
			<img border="0" width="13" height="13" src="/backend/detail/style/images/title_arrow.gif"/> 更新新菜品
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form id="form" action="/food?method=updateFoodById" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="/backend/detail/style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<%-- 定义一个隐藏域，提交菜品对应的Id --%>
						<input type="hidden" name="foodId" value="${food.foodId}" />
						<td width="80px">菜系</td><td>
						<select name="foodTypeId" style="width:80px">
							<c:forEach items="${foodTypes}" var="foodType">
								<c:if test="${foodType.typeName == food.foodType.typeName}">
									<option value="${foodType.typeId}" selected="selected">${foodType.typeName}</option>
								</c:if>
								<c:if test="${foodType.typeName != food.foodType.typeName}">
									<option value="${foodType.typeId}">${foodType.typeName}</option>
								</c:if>
							</c:forEach>
						</select>
						 *</td>
						</tr>
						<tr>
							<td width="80px">菜名</td>
							<td><input id="foodName" type="text" name="foodName" class="InputStyle" value="${food.foodName}" />*</td>
						</tr>
						<tr>
							<td>价格</td>
							<td><input id="price" type="text" name="foodPrice" class="InputStyle" value="${food.foodPrice}"/> *</td>
						</tr>
                        <tr>
							<td>会员价格</td>
							<td><input id="mprice" type="text" name="foodMprice" class="InputStyle" value="${food.foodMprice}"/> *</td>
						</tr>
						
						<tr>
							<td>简介</td>
							<td><textarea id="introduce" name="foodDesc" class="TextareaStyle">${food.foodDesc}</textarea></td>
						</tr>
						<tr>
							<td width="80px">菜品图片</td>
							<td>
								<img style="width: 68px;"
								src="${food.foodImage}">
								<%-- 隐藏域提交的是原图片的信息，防止的是用户不修改--%>
								<input type="hidden" name="oldImage" value="${food.foodImage}">
								<input type="file" name="imageUrl"/> *
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
			<input type="submit" value="修改" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
