<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>欢迎来攀大美味餐厅！</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="/front/detail/style/js/page_common.js"></script>
	<link href="/front/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/front/detail/style/css/index_1.css" />
	<style type="text/css">
		* {
			margin: 0px;
			padding: 0px
		}

		/* 设置底部超链接取消下划线 */
		.list-inline {
			list-style-type: none;
		}

		/* 设置每一个底部超链接的距离及浮动 */
		.list-inline li {
			padding: 20px;
			float: left;
		}

		.list-inline li a {
			font-weight: 500;
			font-size: 18px;
			text-decoration-line: none;
			color: #005A98;
		}

		/* 设置鼠标经过超链接时的样式 */
		.list-inline li a:hover {
			font-weight: 700;
			text-decoration-line: underline;
		}

		/* 设置表格的宽度 */
		.row {
			width: 1100px;
		}

		/* 清除浮动 */
		.row:before,
		.row:after {
			content: "";
			display: table;
		}

		.row:after {
			clear: both;
		}

		.row {
			*zoom: 1;
		}

		/* 设置底部表格的宽度 */
		.footer-table {
			width: 100%;
			text-align: center;
		}

		/* 设置底部文字的描述 */
		.buttom {
			font-weight: 700;
			padding: 20px;
			margin-bottom: 5px;
		}

	</style>

	<script type="text/javascript">

		window.onload = function () {
			//当页面加载完成的时候，就访问/menu/list读取数据

			//在这里使用JavaScript语言发起Ajax请求，访问服务器AjaxServlet中JavaScriptAjax
			//1、我们首先要创建XMLHttpRequest
			let xmlhttprequest = new XMLHttpRequest();
			//2、调用open方法设置请求参数，将请求发送给服务器  将要查询的餐桌的状态一起传过去
			xmlhttprequest.open("GET", "http://localhost:8080/front?method=findTablesByStatus&tableStatus=0", true);

			//4、在send方法前绑定onreadystatechange事件，处理请求完成后的操作。
			xmlhttprequest.onreadystatechange = function () {
				//4表示请求已经完成，且响应数据
				if (xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200) {
					//获取数据
					let jsonObj = JSON.parse(xmlhttprequest.responseText);

					console.log(jsonObj.data); /* 测试代码 */

					let count = 1;
					for (let i = 0; i < jsonObj.data.length; i++) {
						console.log(jsonObj.data[i].tableName); /* 测试代码 */

						if ((i+1)%7 == 0){
							count++;
							document.getElementById("tableId").innerHTML +=
									'<tr><td><ul id="ul_table'+count+'"></ul></td></tr>';
						}

						//把服务器响应的数据显示在页面上
						document.getElementById("ul_table"+count).innerHTML +=
								/* 将餐桌的id，一并传入后面，因为，每个餐桌对应这个点餐的信息 */
								'<li><a href="/front?method=findByPage&tableId='+jsonObj.data[i].tableId+'">'+jsonObj.data[i].tableName+'</a></li><br/>';
					}
				}
			}

			//3、调用send方法发送请求
			xmlhttprequest.send();
		}

	</script>

</head>
<body style="text-align: center">
<!-- 顶部快捷导航start -->

<!--外部的大层-->
<div class="index_all" style="text-align:center;">

	<div id="index_center">
		<div class="shortcut" >
			<span style="text-align: left;font-family: '华文行楷';">攀大美味餐厅——欢迎您！</span>
			<span style="float: right;padding-right: 20px;font-size: 22px;color: black;padding-top: 20px;font-family: '楷体'">
				<c:if test="${loginUser != null}">
					<div>
						<span>
							欢迎：<span style="color: red">${loginUser.nickName}</span>光临就餐
						</span>
						<span>
							&nbsp;&nbsp;&nbsp;累计成功登录次数：<span style="color: red">${loginUser.loginNum+1}</span>次
						</span>
						<br>
						<span style="position: relative;top: 0px;">
							当前就餐人数：<span style="color: red">${onlineCount}</span>人&nbsp;&nbsp;
							时间：${day}
							<a href="/user?method=quit" style="border: 1px solid darkgrey;border-radius: 10px;padding: 6px 5px;background-color: darkgrey;font-size: 15px;margin-left: 20px;">退出登录</a>
						</span>
					</div>
				</c:if>
				<c:if test="${loginUser == null}">
					<span>
						亲：<a href="/"><span style="color: red;font-size: 25px">登录才能点餐哟！！！</span></a>
					</span>
					<span style="position: relative;top: 0px;">
						时间：${day}
					</span>
				</c:if>
			</span>
		</div>

		<img src="/front/detail/style/images/1.jpg"  id="datu"/>
		<!--放桌子的层-->
		<div id="center_bottom">
			<table id="tableId" style="width:700px;position: relative;transform: translate(-400px,-350px);">
				<tr>
					<td>
						<ul id="ul_table1">
						<%-- 餐桌通过异步ajax获取数据 --%>
						</ul>
					</td>
				</tr>
			</table>

		</div>
	</div>
</div>

<div style="margin-top: 480px;">
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
</div>

</body>
</html>