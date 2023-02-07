<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 230207 5교시 kh-study-cloud/backend 03_EL_JSTL JSP 액션 태그 */
	
	request.setCharacterEncoding("UTF-8");	// EL 인코딩
	String year = "2023";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230207 5교시 kh-study-cloud/backend 03_EL_JSTL JSP 액션 태그 -->
	<h2>Include 페이지</h2>
	
	includePage.jsp 의 year 변수 값은 <%= year %> 입니다. <br><br>


	<!-- standard/include.jsp의 57행 jsp:param 값 가져오기 -->
	pName : ${ param.pName } <br>



</body>
</html>