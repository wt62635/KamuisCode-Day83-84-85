<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 
    errorPage="a6.jsp"%>
<html>
<head>			
<title>Insert title here</title>
</head>
<body style="font-size:30px;">
		<%
			String number = 
				request.getParameter("number");
			int num1 = Integer.parseInt(number);
			out.println(num1 + 1);
		%>
</body>
</html>

