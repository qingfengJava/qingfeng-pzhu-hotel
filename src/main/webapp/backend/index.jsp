<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 设置项目虚拟路径 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>攀大美味餐厅点餐系统后台管理</title>
</head>

	<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
		<frame src="/backend/detail/top.jsp" scrolling="no" noresize />
		<frameset cols="178px,*">
			<frame noresize src="/backend/detail/left.jsp" scrolling="yes" />
			<frame noresize name="right" src="/backend/detail/right.jsp" scrolling="yes" />
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="/backend/detail/bottom.jsp" />
	</frameset>
</html>