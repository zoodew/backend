<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    				<!-- 현재 JSP 페이지가 예외를 처리하는 페이지라는 걸 지정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230203 3교시 backend 02_JSP 4.1 page 지시자 -->
	<h1 style="color: red">에러가 발생했습니다. 관리자에게 문의해 주세요.</h1>
	
	<%= exception %>
	<%= exception.getMessage() %>

</body>
</html>