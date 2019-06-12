<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ taglib 
uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body style="font-size:30px;">
	
	<%
		User user = new User();
		user.setUsername("李白");
		user.setGender("m");
		request.setAttribute("user", user);
	%>
	用户名:${user.username} <br/>
	性别 ：
	<c:if test="${user.gender == 'm'}">男</c:if>
	
	
	
	
	
	
	
</body>


</html>