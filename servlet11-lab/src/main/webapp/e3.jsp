<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body style="font-size:30px;">
	<%
		String username = 
			request.getParameter("username");
		out.println(username);
	%>
	${param.username}<br/>
	
	<%
		String[] cities = 
			request.getParameterValues("city");
		out.println(cities[0]);
	
	%>
	<br/>
	${paramValues.city[0]}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>