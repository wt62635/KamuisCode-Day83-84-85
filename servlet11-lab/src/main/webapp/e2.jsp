<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 
    import="java.util.*"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body style="font-size:30px;">
		${1 + 1} <br/>
		${"2" +"3"} <br/>
		
		${1 < 2} <br/>
		<%
			request.setAttribute("s1", "abc");
		%>
		${s1 == 'abc'} <br/>
		${requestScope.s1 == 'abc'} <br/>
		${sessionScope.s1 == 'abc'} <br/>
		
		${2 < 3 && 4 > 5} <br/>
		
		<%
			request.setAttribute("s2", "");
			List list1 = new ArrayList();
			request.setAttribute("list1", list1);
			
		%>
		空字符串:${empty s2} <br/>
		空的集合:${empty list1} <br/>
		值为null:${empty null} <br/>
		找不到对应的值:${empty aaa}
		
		
		
		
		
		
		
		
</body>
</html>