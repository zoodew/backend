<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230206 5교시 kh-study-cloud/backend 02_JSP 6.JSP 내장 객체 영역-->

	<h2>Page 영역와 Request 영역의 비교</h2>
	
	현재 Page 영역에 저장된 데이터 : <%= pageContext.getAttribute("age") %> <br>
	현재 Request 영역에 저장된 데이터 :  <%= request.getAttribute("gender") %>
	
</body>
</html>