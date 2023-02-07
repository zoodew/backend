<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230207 5교시 kh-study-cloud/backend 03_EL_JSTL JSP 액션 태그 -->
	
	<h2>jsp:forward 액션 태그</h2>
	
	<p>
		forward 액션 태그는 다른 페이지로 요청을 전달할 때 사용하는 액션 태그이다.
	</p>
	
	<script>
		alert("안녕하세요.");
	</script>
	<!-- 
		포워드 전에 응답 객체에 쓴 내용은 포워드 되면서 아무런 효과가 없다.
	
		forward를 하면 html태그가 쌓인 버퍼가 비워져서 효과가 발생하지 않음. alert창 생기지 않음. 39행 주석 풀고 묶고 하면서 서버 실행시켜서 어떻게 차이가 나는지 확인
	-->

	<%
		request.setAttribute("userName", "문인수");
		request.setAttribute("userAge", "20");
	%>
	<!-- 
		requestScope의 특징
		forward 하기 전에 값을 넣어줌 그 이후에 forward 하니까 다른 페이지(forwardPage.jsp))에서 값 출력됨
		request 객체 안에 값 넣은 후 EL 구문으로 출력
	 -->

	<%-- <jsp:forward page="forwardPage.jsp"></jsp:forward> --%>
	<jsp:forward page="forwardPage.jsp" />		<!-- 내부에 파라미터(값) 전달 할 거 아니면 한 줄로 작성 가능 -->
	
	



</body>
</html>