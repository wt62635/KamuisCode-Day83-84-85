<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body style="font-size:30px;">
	<%
		User user = new User();
		user.setUsername("King");
		user.setAge(22);
		user.setInterest(
				new String[]{"snooker","fishing"});
		request.setAttribute("user", user);
		
		User user2 = new User();
		user2.setUsername("Sally");
		user2.setAge(33);
		session.setAttribute("user", user2);
	%>
	
	username:<%
		//User user1 =
			//(User)request.getAttribute("user1");
		//out.println(user1.getUsername());
	%>
	<br/>
	
	username:${user.username} <br/>
	指定查询的范围:<br/>
	${sessionScope.user.username}
	
	
	<br/>
	${user['username']}<br/>
	${sessionScope.user['username']}<br/>
	
	<%
		pageContext.setAttribute("s1", "username");
	%>
	${user[s1]}<br/>
	${user[requestScope.s1]}<br/>
	
	${user.interest[0]}<br/>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>