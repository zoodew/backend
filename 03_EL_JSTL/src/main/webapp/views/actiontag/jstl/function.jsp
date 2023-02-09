<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	태그라이브러리 지시자 바로 사용할 수 있는 거 아니고 아래와 같이 작성해 선언해줘야 함
	
	uri		: 태그라이브러리를 사용한다고 식별자처럼 적어주는 것 구분자라고 생각하기
	prefix	: 태그라이브러리에서 주는 태그라이브러리 사용할 때 접두어 뭘로 쓸지 지정
--> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 230208 4교시 kh-study-cloud/backend 03_EL_JSTL JSTL Function -->
태그라이브러리 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Function Library</title>
</head>
<body>

<!-- 230208 3교시 kh-study-cloud/backend 03_EL_JSTL JSTL Function -->
	<h1>JSTL Function Library</h1>
	
	<!-- 변수 선언 -->
	<c:set var="str" value="java oracle HTML5 CSS3 Javascript jQuery Servlet JSP" />
	
	원본 출력 : ${ str } <br><br>
	
	문자열 길이[ fn:length() ] : ${ fn:length(str) } <br><br>
	
	대문자로 변경[ fn:toUpperCase() ] : ${ fn:toUpperCase(str) } <br><br>
	소문자로 변경[ fn:toLowerCase() ] : ${ fn:toLowerCase(str) } <br><br>
	CSS3의 위치[ fn:indexOf( 찾는 곳 , 찾을 값 ) ] : ${ fn:indexOf(str, 'CSS3') } <br><br>
	JSP를 JSTL로 변경[ fn:replace( 찾는 곳, A를 , B로 변경 ) ] : ${ fn:replace(str, 'JSP', 'JSTL') } <br><br>
	
	원본 출력 : ${ str } <br>> 위의 태그라이브러리들은 원본에 영향을 주지 않는다.<br><br>
	

</body>
</html>