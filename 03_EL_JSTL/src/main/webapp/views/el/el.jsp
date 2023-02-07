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
	
	<%-- 강의장 : <%= classRoom %> <br>
	수강생 : <%= student.getName() %>, <%= student.getAge() %> <br> --%>
	
	
<!-- 230206 8교시 kh-study-cloud/backend 03_EL_JSTL -->
	
	<h2>2. EL 방식으로 request, session 객체에 담겨있는 데이터를 출력</h2>
	
	<!--
		1. EL은 영역 객체에 저장된 속성명을 검색해서 존재하는 경우 값을 가져온다.
			- Page 영역 -> Request 영역 -> Session 영역 -> Application 영역 순으로 해당 속성을 찾아서 값을 가져온다.
			  page 영역을 보고 값이 있으면 출력 끝 없으면 request 영역으로 request 영역에 있으면 출력 끝 ...
			  ELServlet.java에 값이 있음
			  
		2. EL은 인스턴스의 필드에 직접 접근하는 것처럼 보이지만 내부적으로는 해당 인스턴스의 Getter 메소드로 접근해서 값을 읽어온다.
			- Student.java에서 Getter 어노테이션 주석 처리하면 오류 발생함.
	-->
	
	<!-- 변수 선언이 안 되어 있는데도 값을 가져올 수 있다. 훨씬 더 간결하게 사용 가능-->
	강의장 : ${ classRoom }<br>
	수강생 : ${ student.name }, ${ student.age }<br><br>

	강의장 : ${ sessionScope.classRoom } <br>	
	수강생 : ${ sessionScope.student.name }, ${ sessionScope.student.age } <br>



<!-- 230207 1교시 kh-study-cloud/backend 03_EL_JSTL -->
	
	<h2>3. EL 사용 시 내장 객체에 저장된 속성명이 같은 경우</h2>
	<%
		pageContext.setAttribute("scope", "Page 영역");
	%>
	
	scope : ${ scope } <br>
	pageScope : ${ pageScope.scope } <br>
	requestScope : ${ requestScope.scope } <br>
	sessionScope : ${ sessionScope.scope } <br>
	apllication : ${applicationScope.scope }
	<!-- ELServlet에 속성 값 넣어둔 것 있음. 페이지 영역에 접근하고 싶으면 pageScope를 통해 접근이 가능 ... 애플리케이션영역에 접근하고 싶으면 applicationScope를 통해 접근이 가능 -->


	<h2>4. ContextPath 가져오기</h2>
	contextPath : 톰캣 서버에 올라가있는 여러 애플리케이션 중에서 내가 원하는 곳을 찾아가는 경로
	<h3>1) 표현식 태그를 사용하는 방법</h3>
	contextPath : 03_EL_JSTL <br>
	contextPath : <%= request.getContextPath() %> <br>
	<!-- 03_EL_JSTL 웹 애플리케이션에 접근하기 위한 경로 -->
	<!-- request에 직접 접근해 getContextPath() 사용  -->
	
	<h3>2) EL을 사용하는 방법</h3>
	ContextPath : ${ pageContext.request.contextPath } <br>
					<!-- request 에 직접 접근하는 것 같지만 get에 접근해서 간접 접근하는 방법 -->


  	<h2>5. 헤더에 접근하기</h2>
	<h3>1) 표현식 태그를 사용하는 방법</h3>
	Host : <%= request.getHeader("Host") %> <br>
			<!-- request 직접 가져와 getHeade사용. 안에 값 들어감 -->
	User-Agent : <%= request.getHeader("User-Agent") %><br>
	
	<h3>2) EL를 사용하는 방법</h3>
	Host : ${ header.Host } <br>
	User-Agent : ${ header['User-Agent'] }



</body>
</html>