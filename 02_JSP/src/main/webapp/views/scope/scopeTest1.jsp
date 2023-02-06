<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230206 1교시 kh-study-cloud/backend 02_JSP 6.JSP 내장 객체 영역-->
	<h2>Session 영역과 Application 영역의 비교</h2>
	
	현재 Session 영역에 저장된 데이터 : <%= session.getAttribute("address") %> <br>
	<!-- 세션 영역은 브라우저를 종료시키기기 전까지 유지 브라우저를 닫고 다시 열면 null 나옴 저장된 데이터가 없음 -->
	현재 Application 영역에 저장된 데이터 : <%= application.getAttribute("name") %><br>
	<!-- 하나의 어플리케이션이 실행돼서 종료될 때까지 유지가 됨 -->





</body>
</html>