<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 230207 6교시 kh-study-cloud/backend 03_EL_JSTL JSTL(JSP Standard Tag Library) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 아래의 "${ pageContext.request.contextPath } 대신 contextPath 넣어줌 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL /JSTL</title>
</head>
<body>

<!-- 230206 6교시 kh-study-cloud/backend 03_EL_JSTL -->

	<h1>EL /JSTL</h1>
	
	<h2>1. EL(Expression Language)</h2>
	<p>
		EL은 JSP 2.0 버전에서 추가된 표현 언어이다. <br>
		표현식 태그를 대신하여 클라이언트에 출력하고자 하는 값들을 좀 더 간결하게 사용하는 방법이다.
	</p>
	
	<h3>1) EL</h3>
	
	<a href="el.do">View Details</a>
	
	
<!-- 230207 2교시 kh-study-cloud/backend 03_EL_JSTL EL 파라미터-->
	
	<h3>2) EL 파라미터</h3>
		
<!-- webapp/index.jsp 위치와 el/elParam.jsp의 위치가 다름 이럴 때 경로 지정하는 법 -->
				<!-- 경로 안에 contextPath 넣을 때 문자 직접 입력하지 말고 아래처럼 el이나 표현식 태그를 통해 contextPath를 출력할 수 있도록 만들기-->
	<form action="${ contextPath }/views/el/elParam.jsp" method="POST">
		<fieldset>
			<legend>제품 입력</legend>
			<input type="text" name="pName" placeholder="제품명을 입력하세요."> <br>
			<input type="number" name="pCount" placeholder="수량을 입력하세요"> <br>
			<input type="text" name="option" placeholder="옵션을 입력하세요."> <br>
			<input type="text" name="option" placeholder="옵션을 입력하세요."> <br>
		
			<br><br>
			
			<input type="submit" value="전송">
		
		</fieldset>
	</form>

	
	<h3>3) EL 연산자</h3>

	<a href="views/el/elOperators.jsp">View Details</a>
	<!-- / 를 생략하면 현재 jsp가 있는 위치에서부터 찾아간다고 생각
	경로 지정시 / 넣으면 톰캣의 루트기 때문에 contextPath까지 주고 풀 경로 적어주기 -->
	

<!-- 230207 4,5교시 kh-study-cloud/backend 03_EL_JSTL JSP 액션 태그 -->
	<h2>2. JSP Action Tag</h2>
	<p>
		JSP 페이지에서 자바 코드를 직접 입력하지 않고 특정 작업을 수행하는데 사용하는 태그이다. <br>
		액션 태그의 경우 웹 브라우저에서 실행되는 것이 아니라 웹 컨테이너에서 실행된다.
	</p>

	<h3>1) 표준 액션 태그</h3>
	<p>
		JSP에서 기본으로 제공하는 액션 태그로 별도의 라이브러리 설치 없이 바로 사용할 수 있다.
	</p>
	
	<a href="${ contextPath }/views/actiontag/standard/include.jsp">jsp:incluede</a>
	<a href="${ contextPath }/views/actiontag/standard/forward.jsp">jsp:forward</a>



<!-- 230207 6교시 kh-study-cloud/backend 03_EL_JSTL JSTL(JSP Standard Tag Library) -->
	
	<h3>2) JSTL(JSP Standard Tag Library)</h3>
	<p>
		JSP Standard Tag Library의 약자로 JSP에서 사용하는 커스텀 태그이다. <br>
		JSP 페이지에서 자주 사용하는 코드들을 사용하기 쉽게 태그로 만들어 표준으로 제공한다.
	</p>	
	
	<h4>2-1) JSPL Core Library</h4>
	<p>
		변수와 URL, 조건문, 반복문 등의 로직과 관련된 액션 태그를 제공한다.
	</p>
	
	<a href="${ contextPath }/views/actiontag/jstl/core.jsp">JSTL Core</a>
	


<!-- 230208 2,3교시 kh-study-cloud/backend 03_EL_JSTL JSTL Formatting Tags -->
	
	<h4>2-2) JSTL Formatting Library</h4>
	<p>
		날짜, 시간, 숫자 데이터의 출력 형식을 지정할 때 사용하는 액션 태그를 제공한다.
	</p>
			<!-- 7행의 contextPath 변수를 통해 EL에 접근해 얻어올 수 있다. -->
	<a href="${ contextPath }/views/actiontag/jstl/formatting.jsp">JSTL Formatting</a>



<!-- 230208 3교시 kh-study-cloud/backend 03_EL_JSTL JSTL Function -->

	<h4>2-3) JSTL Function Library</h4>
	<p>
		문자열 처리에 관한 메소드들을 EL 구문에서 사용할 수 있게 하는 라이브러리이다.
	</p>
	
	<a href="${ contextPath }/views/actiontag/jstl/function.jsp">JSTL Function</a>


</body>
</html>