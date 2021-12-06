<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>找回密码</title>

<link rel="stylesheet" type="text/css" href="css/index.css">

	<style type="text/css">

		/* 设置底部超链接取消下划线 */
		.list-inline{
			list-style-type: none;
		}
		/* 设置每一个底部超链接的距离及浮动 */
		.list-inline li{
			padding: 20px;
			float: left;
		}
		.list-inline li a{
			font-weight: 500;
			font-size: 18px;
			text-decoration-line: none;
		}

		/* 设置鼠标经过超链接时的样式 */
		.list-inline li a:hover{
			font-weight: 700;
			text-decoration-line: underline;
		}

		/* 设置表格的宽度 */
		.row{
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
		.footer-table{
			width: 100%;
			text-align: center;
		}

		/* 设置底部文字的描述 */
		.buttom{
			margin-bottom: 10px;
		}

		#labelCode:hover{
			color: grey;
		}

		.checked:hover{
			color: white;
		}

	</style>

</head>
<body style="background: url(image/1.jpg);background-size:100% 100%;background-attachment: fixed">
<div class="jq22-container" style="padding-top:10px">
	<div class="login-wrap">
		<div class="login-html">

			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
			<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">找回密码</label>
			<div class="login-form">
				<form action="${pageContext.request.contextPath}/user" method="post" onsubmit="return login()">
					<input type="hidden" name="method" value="login" />
					<div class="sign-in-htm">
						<div class="group">
							<label for="username" class="label">用户名</label>
							<input id="username" name="username" type="text" class="input"  >
						</div>
						<div class="group">
							<label for="password" class="label">密码</label>
							<input id="password" name="password" type="password" class="input" data-type="password">
						</div>
						<div class="group">
							<label for="code" class="label">验证码</label>
							<input width=50% type="text" id="code" name="code" style="position: relative;top: 40px; width: 150px;height: 46px;
								font-size: 16px;color: #fff;background: rgba(255,255,255,.1);padding-left: 20px;">
							<a href="javascript:refreshCode()">
								<img src="${pageContext.request.contextPath}/codeServlet" title="看不清点击刷新" id="img_code"
									 style="position: relative;top: 39px; vertical-align: middle"/>
							</a>
							<a href="javascript:refreshCode()" id="labelCode" class="label" style="transform: translate(270px,-20px);font-size: 15px;">看不清？换一张</a>
							<script>
								function refreshCode() {
									document.getElementById("img_code").src = "${pageContext.request.contextPath}/codeServlet?" + (new Date()).getTime();
								}
							</script>
						</div>
						<div class="group">
							<input id="check" type="checkbox" name="check" value="1" class="check"/>
							<label class="checked" for="check" style="position: relative;top: 40px;"><span class="icon"></span>记住密码</label>
						</div>
						<div class="group">
							<input type="submit" class="button" value="登录" onclick="return login()" style="width: 48%">
							<input type="reset" class="button" value="重填" style="width: 48%;margin-left: 5px">
						</div>
						<div class="hr"></div>
					</div>
				</form>
				
				<form action="${pageContext.request.contextPath}/user" method="post" onsubmit="return register()">
					<input type="hidden" name="method" value="update" />
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label">用户名</label>
							<input id="user" name="username" type="text" class="input"  >
						</div>
						<div class="group">
							<label for="pass" class="label">新密码</label>
							<input id="pass" name="password" type="password" class="input" data-type="password" placeholder="字母、数字、下划线、点(6-16)位" autocomplete="off">
						</div>
						<div class="group">
							<label for="pass" class="label">验证码</label>
							<input width=50% type="text" id="inputCode" name="code" style="position: relative;top: 40px; width: 150px;height: 46px;
								font-size: 16px;color: #fff;background: rgba(255,255,255,.1);padding-left: 20px;">
							<a href="javascript:refCode()">
								<img src="${pageContext.request.contextPath}/codeServlet" title="看不清点击刷新" id="img_code2"
									 style="position: relative;top: 39px; vertical-align: middle"/>
							</a>
							<a href="javascript:refreshCode()" id="labelCode" class="label" style="transform: translate(270px,-20px);font-size: 15px;">看不清？换一张</a>
							<script>
								function refCode() {
									document.getElementById("img_code2").src ="${pageContext.request.contextPath}/codeServlet?" + (new Date()).getTime();
								}
							</script>
						</div>						
						<div class="group">
							<input type="submit" class="button" value="找回密码">
						</div>
						
						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="/index.jsp">重新注册?</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- footer -->
<table class="footer-table">
	<tr>
		<td align="center">
			<div class="row">
				<ul class="list-inline">
					<li style="margin-left: 45px;"><a href="#">关于我们</a></li>
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
				<span style="font-weight: 700">Copyriht &copy; 2021-2022 攀枝花学院&nbsp;&nbsp;&nbsp;&nbsp; 版权归属：Web应用开发专业团队</span>
			</div>
		</td>
	</tr>
</table>

</body>
<script>

    //检验验证码是否正确
    function login() {
		var username=document.getElementById("username").value;
		var password=document.getElementById("password").value;
        //获取输入的验证码
        var inputCode = document.getElementById("code").value;

		if(username.trim()==""&&password.trim()==""){
			alert("请输入用户名和密码！")
			return false;
		}else if(username.trim()==""){
			alert("请输入用户名!")
			document.getElementById("password").value="";
			return false;
		}else if(password.trim()==""){
			alert("请输入密码！")
			return false;
		}else if (inputCode.length <= 0) {
            alert("请输入验证码！");
            return false;
        } else {
            return true;
        }
    }

	function register(){
		
		var reg_pwd = /^[a-zA-Z0-9_.]{6,16}$/;
		
		var pwd = document.getElementById("pass").value;
		
		
		 if(!reg_pwd.test(pwd)){
			alert("密码的格式不正确！")
			clearPwd()
			return false;
		}
		 else {
			return true;
		}
	}
	
	function clearPwd() {
		document.getElementById("pass").value = "" //设置为空
		
	}
</script>
</html>