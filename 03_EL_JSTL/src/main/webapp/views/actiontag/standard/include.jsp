<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230207 4 5교시 kh-study-cloud/backend 03_EL_JSTL JSP 액션 태그 -->

	<h1>jsp:include 액션 태그</h1>
	<p>
		include 액션 태그는 다른 페이지를 포함 시킬 때 사용하는 액션 태그이다.
	</p>

<!-- 230207 5교시 kh-study-cloud/backend 03_EL_JSTL JSP 액션 태그 -->	
	
	<h2>1. include 지시어</h2>
	<p>
		다른 페이지(includePage.jsp)를 포함하는 JSP 파일이 컴파일 되기 전에 삽입된다. 
	</p>
	
	<%--
	<%@ include file="includePage.jsp" %>
	<!-- includePage.jsp가 서블릿으로 바뀌는 것이 아니라 현재 include.jsp가 서블릿으로 변경될 때 includePage.jsp가 include.jsp에 포함이 됨 -->
	
	<%
		String year = "2024";
		// 현재 페이지에 있는 변수명과 포함하는 페이지에 있는 변수명이 중복되기 때문에 에러가 발생.
	%>
	--%>
	
	
	<h2>2. include 액션 태그</h2>
	<p>
		다른 페이지를 포함하는 JSP파일이 화면에 출력되는 시점(런타임)에 삽입된다.
	</p>
	
	<%-- <jsp:include page="includePage.jsp"></jsp:include> --%>	<!-- include 액션 태그 표현방식 1 종료태그 -->
	<jsp:include page="includePage.jsp" />							<!-- include 액션 태그 표현방식 2 종료태그 없이 시작태그 마지막에 슬래쉬/-->

	<!-- 하나의 페이지에 다른 페이지를 포함하는 것이 아니라 includePage로 넘어갔다가 다시 돌아오는 방식 -->

	<%
		String year = "2024";
	%>
	
	<br><br>
	include.jsp 페이지의 year 변수값은 <%= year %> 입니다.
		<!-- 페이지가 포함되는 것이 아니라 변수명이 같아도 중복 에러가 발생하지 않는다. -->


	<br><br>
	
	<!--
		jsp:param 액션 태그를 이용해서 포함되는 페이지로 값을 전달할 수 있다.
	-->
	<jsp:include page="includePage.jsp">
		<jsp:param name="pName" value="아이폰 12 미니"/>
	</jsp:include>


	
</body>
</html>