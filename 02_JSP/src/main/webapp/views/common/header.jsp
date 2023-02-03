
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    					<!-- errorPage="../error/error.jsp" 를 5행에 추가 에러 발생시 error.jsp로 연결 -->
    					<!-- 페이지 내에서 에러 처리 페이지를 직접 지정 -->
    					<!-- error404, error500 확인하기 위해서는 errorPage="" 지우고 테스트
    						페이지 내에 작성한 속성이 제일 우선순위 -->
    		<!-- 4.1 페이지 지시자 속성 | 상위에 error라는 폴더의 error.jsp -->
    
<%
	LocalDateTime now = LocalDateTime.now();
	String today = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
	
	// 에러 페이지 확인을 위한 코드
	/* int result = 10 / 0; */
%>	<!-- int result = 10 / 0; 일부러 에러 발생
		erroePage 속성 isErrorpage 속성 사용
		'http 상태 500 - 내부 서버 오류' 창을 대신해 에러페이지 처리하는 속성-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지시자 태그</title>
</head>
<body>

<!-- 230203 3교시 backend 02_JSP -->
<%-- 		<h1>지시자 태그</h1>
		오늘은 <%= today %>입니다. --%>


<!-- 230203 5교시 backend 02_JSP 4.1 include 지시자 -->
	<header>
		<h1>지시자 태그</h1>
		오늘은 <%= today %>입니다.
	</header>