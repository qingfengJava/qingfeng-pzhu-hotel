<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>攀大美味餐厅</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/backend-style/js/page_common.js"></script>
<link href="/backend-style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/backend-style/css/index_1.css" />

	<script type="text/javascript">

		window.onload = function (){
			//当页面加载完成的时候，就访问/menu/list读取数据

			//在这里使用JavaScript语言发起Ajax请求，访问服务器AjaxServlet中JavaScriptAjax
			//1、我们首先要创建XMLHttpRequest
			let xmlhttprequest = new XMLHttpRequest();
			//2、调用open方法设置请求参数，将请求发送给服务器
			xmlhttprequest.open("GET","http://localhost:8080/food?method=findFoodTypeList",true);

			//4、在send方法前绑定onreadystatechange事件，处理请求完成后的操作。
			xmlhttprequest.onreadystatechange = function (){
				//4表示请求已经完成，且响应数据
				if (xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200){
					//获取数据
					let jsonObj = JSON.parse(xmlhttprequest.responseText);

					console.log(jsonObj); /* 测试代码 */

					for (let i = 0; i < jsonObj.length; i++) {
						console.log(jsonObj[i]); /* 测试代码 */
						//把服务器响应的数据显示在页面上
						document.getElementById("foodTypeList").innerHTML +=
								"<option align='center' value="+jsonObj[i].typeId+" >"+jsonObj[i].typeName+"</option>";
					}

				}
			}

			//3、调用send方法发送请求
			xmlhttprequest.send();


			//对提交做判断，不允许出现空值
			document.getElementById("form").onsubmit = function () {
				//获取各个输入框的对象
				let foodName = document.getElementById("foodName");
				let price = document.getElementById("price");
				let mprice = document.getElementById("mprice");
				let introduce = document.getElementById("introduce");
				let file = document.getElementById("file");

				if (foodName.value != "" && price.value != "" && mprice.value != "" && introduce.text != "" && file.value != ""){
					if (mprice.value < price.value){
						return confirm("Are you sure you want to add food?\r\n你确定要添加菜品吗？");
					}else {
						alert("会员定价不符合常理！");
						return false;
					}
				}else {
					alert("提交信息不能为空！");
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
			<img border="0" width="13" height="13" src="/backend-style/images/title_arrow.gif"/> 添加新菜品
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form id="form" action="/food?method=save" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="/backend-style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="80px">菜系</td>
							<td>
                            <select id="foodTypeList" name="typeId" style="width:80px">
								<%-- 通过异步ajax发丝东请求进行渲染 --%>
								<%--<option value="1" >粤菜</option>
								<option value="2" >川菜</option>
								<option value="3" >湘菜</option>
								<option value="4" >东北菜</option>--%>
                            </select>
                             *</td>
						</tr>
						<tr>
							<td width="80px">菜名</td>
							<td><input id="foodName" type="text" name="foodName" class="InputStyle" value=""/> *</td>
						</tr>
						<tr>
							<td>价格</td>
							<td><input id="price" type="text" name="foodPrice" class="InputStyle" value=""/> *</td>
						</tr>
                        <tr>
							<td>会员价格</td>
							<td><input id="mprice" type="text" name="foodMprice" class="InputStyle" value=""/> *</td>
						</tr>
						
						<tr>
							<td>简介</td>
							<td><textarea id="introduce" name="foodDesc" class="TextareaStyle"></textarea></td>
						</tr>
						<tr>
							<td width="80px">菜品图片</td>
							<td>
								<input id="file" type="file" name="imageUrl"/> *
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
			<input type="submit" value="添加" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
