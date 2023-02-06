<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!-- 230206 3교시 kh-study-cloud/backend 02_JSP 5.JSP 내장 객체 -->
<%
	/*
	  sendRedirect(String url)
		- 매개값으로 지정한 URL로 요청을 재전송한다.
		  브라우저에 표시되는 URL이 변경된다. 'http://localhost:8088/02_JSP/views/objects/redirect_target.jsp'
		- 리다이렉트 방식은 이동할 페이지로 요청과 응딥 객체를 새로 생성하여 전송하므로 이전 요청과 응답 정보가 유지되지 않는다.
	*/
	response.sendRedirect("redirect_target.jsp");
%>
