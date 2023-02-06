<%@page import="com.kh.el.vo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 기본 방식으로 가져오기 230206 7교시-->
<%
	// Request 영역에 저장된 속성(Attribute)을 가져온다.
	/* String classRoom = (String) request.getAttribute("classRoom");
	Student student = (Student) request.getAttribute("student"); */
	
	// Session 영역에 저장된 속성(Attribute)을 가져온다.
	/* String classRoom = (String) session.getAttribute("classRoom");
	Student student = (Student) session.getAttribute("student"); */
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230206 7교시 kh-study-cloud/backend 03_EL_JSTL -->

	<h1>EL</h1>

	<h2>1. 기존 방식으로 request, session 객체에 담겨있는 데이터를 출력</h2>	
	<%--
	강의장 : <%= classRoom %> <br>
	수강생 : <%= student.getName() %>, <%= student.getAge() %> <br>
	--%>
	
<!-- 230206 8교시 kh-study-cloud/backend 03_EL_JSTL -->
	
	<h2>2. EL 방식으로 request, session 객체에 담겨있는 데이터를 출력</h2>
	
	<!--
		1. EL은 영역 객체에 저장된 속성명을 검색해서 존재하는 경우 값을 가져온다.
			- Page 영역 -> Request 영역 -> Session 영역 -> Application 영역 순으로 해당 속성을 찾아서 값을 가져온다.
			  page 영역을 보고 값이 있으면 출력 끝 없으면 request 영역으로 request 영역에 있으면 출력 끝 ...
			  ELServlet.java에 값이 있음
			  
		2. EL은 인스턴스의 필드에 직접 접근하는 것처럼 보이지만 내부적으로는 해당 인스턴스의 Getter 메소드로 접근해서 값을 읽어온다.
			- Student.java에서 게터 어노테이션 주석 처리하면 오류 발생함.
	-->
	<!-- 변수 선언이 안 되어 있는데도 값을 가져올 수 있다. 훨씬 더 간결하게 사용 가능-->
	강의장 : ${ classRoom }<br>
	수강생 : ${ student.name }, ${ student.age }<br><br>

	강의장 : ${ sessionScope.classRoom } <br>	
	수강생 : ${ sessionScope.student.name }, ${ sessionScope.student.age } <br>




</body>
</html>