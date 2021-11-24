<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>攀大点餐系统——添加菜系</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/backend/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="/backend/detail/style/js/page_common.js"></script>
<link href="/backend/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/backend/detail/style/css/index_1.css" />

	<script type="text/javascript">

		window.onload = function() {

			let typeName = document.getElementsByName("typeName")[0];

			//给添加按钮绑定事件
			document.getElementById("form").onsubmit = function () {
				if (typeName.value != ""){
					return confirm("Are you sure you want to add foodType? \r\n你确定要添加菜系信息吗？");
				}else {
					alert("请输入要添加菜系的名称！");
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
			<img border="0" width="13" height="13" src="/backend/detail/style/images/title_arrow.gif"/>  添加菜系
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form id="form" action="/foodtype" method="post">
		<%-- 因为是post提交，那么请求的参数传值建议使用隐藏域提交 --%>
		<input type="hidden" name="method" value="save" />
	
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="/backend/detail/style/images/item_point.gif"> 菜系信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="80px">菜系名称</td>
							<td>
								<input type="text" name="typeName" class="InputStyle" placeholder="请输入菜系的名称..." value=""/> *
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
