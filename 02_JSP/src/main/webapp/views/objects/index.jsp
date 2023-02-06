<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 내장 객체</title>
</head>
<body>

<!-- 230206 1교시 kh-study-cloud/backend 02_JSP 5.JSP 내장 객체 -->
	<h1>JSP 내장 객체</h1>
	
	<%-- <%
		String.out = "";
			// 내장객체과 동일한 이름으로 객체를 생성할 수 없다.
		out.write("<h3>안녕하세요.</h3>");
		
	%> --%>
	
<!-- 230206 2교시 kh-study-cloud/backend 02_JSP 5.JSP 내장 객체 -->
	<h2>1. rquest 객체</h2>
	<p>
		웹 브라우저의 요청 정보를 가지고 있는 객체이다.
	</p>
	
	<h3>1) 헤더와 관련된 메소드</h3>
	
	<table border="1">
		<tr>
			<th>헤더 이름</th>
			<th>헤더 값</th>
		</tr>
		<%
			/* String userAgent = request.getHeader("User-Agent");		
			System.out.println(userAgent); */
			
			/* 클라이언트가 전달해준 모든 값에 접근해 출력하는 방법 */
			Enumeration<String> headerNames = request.getHeaderNames();
			
			while(headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				
				/* System.out.println(headerName);
				System.out.println(request.getHeader(headerName));
				System.out.println(); */
		%>
			<tr>
				<td><%= headerName %></td>
				<td><%= request.getHeader(headerName) %></td>
			</tr>
		<%
			}
		%>
	</table>
	
	
	<h3>2) URL / URI, 요청 방식과 관련된 메소드</h3>
	
	<table border="1">
		<tr>
			<th>이름</th>
			<th>값</th>
		</tr>
		<tr>
			<td>서버 도메인명</td>
			<td><%= request.getServerName() %></td>
		</tr>
		<tr>
			<td>서버 포트 번호</td>
			<td><%= request.getServerPort() %></td>
		</tr>
		<tr>
			<td>서버 URL</td>
			<td><%= request.getRequestURL() %></td>
		</tr>
		<tr>
			<td>서버 URI</td>
			<td><%= request.getRequestURI() %></td>
			<!-- 에러 뜨는 거 버그 무시하기 -->
		</tr>
		<tr>
			<td>프로토콜</td>
			<td><%= request.getProtocol() %></td>
		</tr>
		<tr>
			<td>요청 방식</td>
			<td><%= request.getMethod() %></td>
		</tr>
		<tr>
			<td>요청 쿼리</td>
			<td><%= request.getQueryString() %></td>
		</tr>
		<!-- 출력에 null 뜨는 거 url에 http://localhost:8088/02_JSP/views/objects/?userName=yk$userAge=27 라고 치면 userName=yk$userAge=27 출력-->
		
<!-- 230206 3교시 kh-study-cloud/backend 02_JSP 5.JSP 내장 객체 -->
		<tr>
			<td>웹 애플리케이션 경로</td>
			<td><%= request.getContextPath() %></td>
		</tr>
		<tr>
			<td>클라이언트 호스트명</td>
			<td><%= request.getRemoteHost() %></td>
		</tr>
		<tr>
			<td>클라이언트 IP 주소</td>
			<td><%= request.getRemoteAddr() %></td>
		</tr>
	</table>

	<h2>2. response 객체</h2>
	<p>
		웹 브라우저의 요청에 대한 응답 객체이다.
	</p>
	
	<h3>1) Redirect</h3>
	<p>
		redirect.jsp로 넘어온 요청을 재전송해 redirect_target.jsp로 넘김 <br>
		> url이 redirect_target.jsp로 나옴 <br>
		cf) forward
	</p>
	
	<a href="redirect.jsp">View Details</a>
	<!-- 앵커태그에는 redirect.jsp를 경로설정했는데 열린 페이지는 redirect_target.jsp -->

	<h2>3. pageContext 객체</h2>
	<p>
		JSP에서 다른 내장 객체를 얻어내거나 현재 페이지의 요청과 응답의 제어권을 다른 페이지로 넘겨주는 데 사용하는 객체이다.
	</p>
	
	<h3>1) Forward</h3>
	<p>
		forward.jsp의 내부에서 forward_target.jsp로 넘겨서 페이지를 받아옴 <br>
		> url이 forward.jsp로 나옴 <br>
		cf) redirect
	</p>
	<a href="forward.jsp">View Details</a>

<!-- 230206 4교시 kh-study-cloud/backend 02_JSP 5.JSP 내장 객체 
	리다이렉트 포워드 비교-->
	
	<h2>4. session 객체</h2>
	<p>웹 브라우저의 정보를 유지하기 위한 세션 정보를 저장하고 있는 객체이다.</p>
	
	클라이언트와 서버의 상태(관계)를 유지하기 위해 제공되는 기능 <br>
	jsp에서 사용하기 위해 만들어진 게 session() 객체 <br>
	세션 유지는 하나의 브라우저에 접속해서 그 브라우저가 종료될때까지만 유지 <br><br>
	
	<%
		session.setAttribute("userId", "ismoon");
		session.setMaxInactiveInterval(5);
	/*
		setMaxInactiveInterval(5)
			클라이언트의 요청이 없더라도 세션을 유지할 시간을 초 단위로 설정한다.
			5초까지 아무런 요청이 없으면 다시 새로 생성
	*/
	
		Cookie cookie = new Cookie("userName", "문인수");
		response.addCookie(cookie);
		/* 개발자 도구의 애플리케이션 쿠키 하단에 값이 들어가 있는 거 확인 가능 */
		
		
		/* Cookie[] cookies = request.getCookies();
	
		Arrays.stream(cookies).forEach(c -> System.out.println(cookie.getValue())); */
	
	%>
	세션 ID : <%= session.getId() %> <br>
	isNew() : <%= session.isNew() %> <br>
	<!-- isNew를 true로 만들려면 url 복사해서 창 다 껐다가 다시 켜서 복붙하기 -->
	생성 시간 : <%= new Date(session.getCreationTime()) %> <br>
	최종 접속 시간 : <%= new Date(session.getLastAccessedTime()) %> <br>
	userId : <%= session.getAttribute("userId") %>

	
	<h2>5. application 객체</h2>
	<p>
		웹 애플리케이션(컨텍스트)의 실행 환경을 제공하는 서버(ex tomcat)의 정보를 저장하고 있는 객체이다.
	</p>
	
	<table border="1">
		<tr>
			<td>컨테이너 정보</td>
			<td><%= application.getServerInfo() %></td>
		</tr>
		<tr>
			<td>웹 모듈 버전</td>
			<td><%= application.getMajorVersion() %>.<%= application.getMinorVersion() %></td>
		</tr>
		<tr>
			<td>웹 애플리케이션의 실제 파일 시스템 경로</td>
			<td><%= application.getRealPath("/") %></td>
		</tr>					<!-- "/" webapp을 가리킴 그 아래 경로를 찾으려면 /views/objects/redirect.jsp 이런 식으로 찾아가면 됨-->
	</table>










<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>

</body>
</html>