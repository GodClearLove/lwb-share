<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
	<h3>请注册</h3>
	<form method="post" action="${pageContext.request.contextPath}/user/regist">
		手机号：<input type="text" name="phone"/><br/>
		密码：<input type="password" name="password"><br/>
		省份：<input type="text" name="province"><br/>
		城市：<input type="text" name="city"><br/>
		注册日期：<input id="formDate" type="text" name="regDate"/><br/>
		<input type="submit" name="submit" value="注册">
	</form>
</body>
</html>