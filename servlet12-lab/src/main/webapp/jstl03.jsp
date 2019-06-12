<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<html>
<head>
<title>Insert title here</title>
<style>
	.row1{
		background-color:#fff8dc;
	}
	.row2{
		background-color:#f0f0f0;
	}
</style>
</head>
<body style="font-size:30px;">
	<%
		List<User> users = 
				new ArrayList<User>();
		for(int i = 0; i < 8; i ++){
			User user = new User();
			user.setUsername("用户" + i);
			user.setGender("m");
			user.setAge(22 + i);
			users.add(user);
		}
		request.setAttribute("users", users);
	%>
	<table border="1" width="60%" 
		cellpadding="0" cellspacing="0">
		<tr>
			<td>用户名</td><td>性别</td>
			<td>年龄</td><td>index</td>
			<td>count</td>
		</tr>
		<c:forEach items="${users}" var="u" 
		varStatus="s">
			<tr class="row${s.index % 2 + 1}">
				<td>${u.username}</td>
				<td>${u.gender}</td>
				<td>${u.age}</td>
				<td>${s.index}</td>
				<td>${s.count}</td>
			</tr>
		</c:forEach>
	</table>
	
	
	
	
	
	
	
	
	
	
</body>
</html>