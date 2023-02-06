<%@page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!-- 230203 6교시 02_JSP 폼 파라미터 값 읽어오기 /directive index.jsp의 폼태그에 입력된 값-->
<!-- 230206 1교시 폼 파라미터 값을 읽어오기 전에 인코딩 설정을 해야 directive/index의 method가 POST방식이어도 한글이 다 출력된다. -->
<%

	// 인코딩 설정 230206 1교시
	request.setCharacterEncoding("UTF-8");
	// 폼 파라미터 값 읽어오기		230203 6교시
	// r 폼 파라미터 배열 생성은 String 타입으로만 값을 받음
	String userName = request.getParameter("userName");
	String userAge = request.getParameter("userAge");
	String gender = request.getParameter("gender");
	String[] foods = request.getParameterValues("food");	// 음식 중 선택된 값들을 foods 배열에 다 받아옴


	System.out.println(userName);
	System.out.println(userAge);
	System.out.println(gender);
	/* Arrays.stream(foods).forEach((String food) -> {System.out.println(food);}); */	/* 18 - 20 동일 람다식 표현 */
	Arrays.stream(foods).forEach(food -> System.out.println(food));
	/* Arrays.stream(foods).forEach(System.out::println); */
%>
  
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 출력</title>
</head>
<body>

<!-- cf) 01_Servlet src.main.java.com.kh.servlet MethodServlet -->
	<h2>개인 정보 출력</h2>
	<%= userName %>은 <%= userAge %>세이고 성별은 <%= gender %>입니다. <br>

	좋아하는 음식은
	<!-- 방법1) -->
	<%-- 	
		<%
			for(String food : foods) {
				out.print(food + " ");
			}
		%>
	--%>
	<!-- 방법2) -->
		<%
			for(String food : foods) {
		%>
			<span style="color: red">
				<%= food %>
			</span>
		<%
			}
		%>
	입니다.


</body>
</html>