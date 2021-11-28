<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Frame left</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="/backend/detail/style/js/page_common.js"></script>
	<script type="text/javascript" src="/backend/detail/style/js/jquery.js"></script>
    <link href="/backend/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		// 显示或隐藏二级菜单
		function menuClick( menuDiv ){
			$(".MenuLevel2").not( $(menuDiv).next() ).hide();
			$(menuDiv).next().toggle();
		}

		window.onload = function (){
			//当页面加载完成的时候，就访问/menu/list读取数据

			//在这里使用JavaScript语言发起Ajax请求，访问服务器AjaxServlet中JavaScriptAjax
			//1、我们首先要创建XMLHttpRequest
			let xmlhttprequest = new XMLHttpRequest();
			//2、调用open方法设置请求参数，将请求发送给服务器
			xmlhttprequest.open("GET","http://localhost:8080/menu/list",true);

			//4、在send方法前绑定onreadystatechange事件，处理请求完成后的操作。
			xmlhttprequest.onreadystatechange = function (){
				//4表示请求已经完成，且响应数据
				if (xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200){
					//获取数据
					let jsonObj = JSON.parse(xmlhttprequest.responseText);
					console.log(jsonObj); /* 测试代码 */

					for (let i = 0; i < jsonObj.data.length; i++) {
						//把服务器响应的数据显示在页面上
						document.getElementById("menuId").innerHTML +=
								"<li class=\"level2 level2Style\">\n"+
									"<a target=\"right\" href="+jsonObj.data[i].menuUrl+">"+jsonObj.data[i].menuName+"</a>\n"+
								"</li>";
					}

				}
			}

			//3、调用send方法发送请求
			xmlhttprequest.send();

			// 默认只显示第1个二级菜单
			document.getElementById("menuId").hidden;
			/*$(".MenuLevel2").hide();
			$(".MenuLevel2:first").show();*/
		}

	</script>
<!-- 内容总宽度为 3px边框 * 2 + 155px内容 = 161px; -->
<style type="text/css">
<!--
html{
height: 100%;
}
body {
	background: none repeat scroll 0 0 #D8EDFC;
	margin: 0;
	padding: 0;
}
#Menu {
    margin: 0;
    padding: 0;
    width: 155px;
	background: none repeat scroll 0 0 #D8EBF7;
    list-style: none outside none;
	
	margin-left: 3px;
	border-top: 3px solid #4891C6;
}
#Menu .level1 {
 color: #005790;
    font-weight: bold;
    padding-bottom: 1px;
	  cursor: pointer;
}
#Menu .level1 .level1Style {
  background: url("style/images/img/menu_btn_bg.gif") no-repeat scroll 0 0 transparent;
    height: 23px;
    padding-left: 20px;
    padding-top: 5px;
    width: 135px;
	margin-bottom: -4px
}
#Menu .level1 .level1Style .Icon {
	margin-top: -2px;
}
#Menu .level1 .MenuLevel2 {
 background: none repeat scroll 0 0 #D8EBF7;
    list-style: none outside none;
    margin: 0;
    padding: 0;
}
#Menu .level1 .MenuLevel2 .level2Style{
	color: #005790;
    font-weight: normal;
	border-top: 1px solid #EFF6FB;
	height: 18px;
	padding-left: 43px;
	padding-top: 5px;
	width: 112px;
	background-image:url(style/images/img/menu_arrow_single.gif);
	background-color: #8EC4E9;
	background-repeat: no-repeat;
	background-position: 29px center;
}
-->
	</style>
</head>
<body>
	
    <ul id="Menu">
	    <li class="level1">
            <div onClick="menuClick(this);" class="level1Style">
				<img src="/backend/detail/style/images/func20001.gif" class="Icon" />
				系统菜单
			</div>
            <ul class="MenuLevel2" id="menuId">
				<%-- 遍历后端查到的数据，在javascript中通过异步ajax请求实现 --%>
				<%--<c:forEach items="${menus}" var="menu">
					<li id="child" class="level2 level2Style">
						<a target="right" href="${menu.menuUrl}">${menu.menuName}</a>
					</li>
				</c:forEach>--%>

            </ul>
        </li>
    </ul>
</body>
</html>