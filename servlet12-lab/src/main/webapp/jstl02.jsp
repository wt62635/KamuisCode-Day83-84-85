<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body style="font-size:30px;">
	<%
		User user = new User();
		user.setUsername("李白");
		user.setGender("x");
		user.setAge(33);
		request.setAttribute("user", user);
	%>
	性别:<c:choose>
		<c:when test="${user.gender == 'm'}">
			男
		</c:when>
		<c:when test="${user.gender == 'f'}">
			女
		</c:when>
		<c:otherwise>
			保密
		</c:otherwise>
	</c:choose>
	年龄:
	<c:choose>
		<c:when test="${user.age < 18}">未成年</c:when>
		<c:when test="${user.age > 65}">老年</c:when>
		<c:otherwise>成年</c:otherwise>
	</c:choose>
	
	
	
	
	
	
	
	
	
	
	
</body>

</html>