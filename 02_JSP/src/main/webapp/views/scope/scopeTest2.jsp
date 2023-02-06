<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!-- 230206 5교시 kh-study-cloud/backend 02_JSP 6.JSP 내장 객체 영역-->

<%
	pageContext.setAttribute("age", "19");
	request.setAttribute("gender", "남자");

	System.out.println(pageContext.getAttribute("age"));
	
	//pageContext.forward("scopeTest3.jsp");
	// scopeTest3 > null 남자 가 찍힘 
	/* forward > 하나의 요청을 다시 넘기지 않고 요청이 유지 scopeTest2 내부에서 처리하고 처리된 내용을 통해 응답 */
	
	response.sendRedirect("scopeTest3.jsp");
	// null null 이 찍힘
	/* redirect > 사용자가 브라우저에서 서버로 요청을 보내면(scopeTest2) 한번 응답을 보내고 다시 요청함(scopeTest3) */
%>