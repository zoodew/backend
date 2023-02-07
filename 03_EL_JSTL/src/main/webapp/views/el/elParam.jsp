<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	// method가 POST 방식이면 한글 다 깨져서 나옴 인코딩 설정
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230207 2교시 kh-study-cloud/backend 03_EL_JSTL -->

	<h1>주문 내역</h1>
	<p>
		param		: 전달된 파라미터 값을 받아올 때 사용하는 내장 객체이다. <br>
		paramValues	: 전달된 파라미터 값"들"을 배열로 받아올 때 사용하는 내장 객체이다.
	</p>
	
<!-- webapp/index.jsp 위치와 el/elParam.jsp의 위치가 다름 이럴 때 경로 지정하는 법 03_EL_JSTL/src/main/webapp/index.jsp 가서 보기-->
	<p>- pram 내장 객체</p>
	상품명 : ${ param.pName } <br>
	수량 : ${ param.pCount } <br><br>
	<!-- param이라는 내장 객체를 통해 webapp/index.jsp의 input 태그의 name 속성에 해당하는 value 속성을 받아옴 input 태그로 전달 받은 값을 출력함
		더 간결하게 코드 작성 가능 -->
	
	<p>- pram 내장 객체로 여러 값이 있는 속성 받아오기</p>
	옵션 : ${ param.option } <br><br>
	<!-- 단순히 param 내장 객체 쓰면 여러 값이 있는데 첫 번째 값에만 접근해서 출력함 -->
	<!-- 하나의 name 속성에 여러 value가 있으면 paramValues 내장 객체를 사용해서 여러 값을 가져온다. -->
	<p>- pramValues 내장 객체</p>
	옵션 : ${ paramValues.option[0] } <br>
	옵션 : ${ paramValues.option[1] } <br>

</body>
</html>