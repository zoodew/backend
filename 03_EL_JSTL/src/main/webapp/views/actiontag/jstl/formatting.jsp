<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 230208 2교시 kh-study-cloud/backend 03_EL_JSTL JSTL Formatting Tags -->
<!-- 
	태그라이브러리 지시자 바로 사용할 수 있는 거 아니고 아래와 같이 작성해 선언해줘야 함
	
	uri		: 태그라이브러리를 사용한다고 식별자처럼 적어주는 것 구분자라고 생각하기
	prefix	: 태그라이브러리에서 주는 태그라이브러리 사용할 때 접두어 뭘로 쓸지 지정
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Formatting Library</title>
</head>
<body>

<!-- 230208 2교시 kh-study-cloud/backend 03_EL_JSTL JSTL Formatting Tags -->

	<h1>JSTL Formatting Library</h1>
	
	
<!-- 230208 3교시 ~40행 kh-study-cloud/backend 03_EL_JSTL JSTL Formatting Tags -->
	<!--
	setLocale 태그
		지역 설정을 통해 통화 기호나 시간 대역을 다르게 설정 가능하다.
	-->
	톰캣 서버의 Locale : ${ pageContext.response.locale } <br>			<!-- 톰캣 서버의 Locale : ko_KR -->
	
	<!-- 톰캣 서버의 Locale이 ko_KR였는데 이것을 ja-JP로 바뀜 이를 통해 2.fmt:formatDate 태그 출력 방법이 다르게 나오는 거 알 수 있음 -->
	<!-- 액션태그들 jsp 주석으로 가려줘야 먹힘 -->
	<%--
	<fmt:setLocale value="ja-JP"/>
	<fmt:setLocale value="en-US"/>
	--%>
	
	
		
	<h2>1.formatNumber 태그</h2>
	<p>
		숫자 데이터의 출력 형식을 지정할 때 사용하는 태그이다.
	</p>		
	
	<!--
		groupingUsed : 숫자 단위의 구분자(,) 표시 여부 지정(기본값 : true)
	-->

	숫자 그대로 춢력 : <fmt:formatNumber value="123456789" groupingUsed="flase" /> <br>
							<!-- 123456789 출력. groupingUsed="flase"-->	
	세 자리마다 구분하여 춢력 : <fmt:formatNumber value="123456789" groupingUsed="true" /> <br><br>
							<!-- 123,456,789 출력. 자리수마다 콤마(,) 지정 groupingUsed="true"(기본값) -->	
	
	<!--
		pattern :	화면에 표시할 숫자 데이터의 패턴을 지정한다.
					#. 0을 패턴 기호로 사용하여 지정한다.
					패턴 기호가 부족한 부분에 대해서는 #은 표시하지 않지만 0은 0으로 표시한다.
					패턴 기호를 초과하는 부분은 반올림된다.
	-->
	pattern="#" : <fmt:formatNumber value="1.23" pattern="#" /> <br>			<!-- 1 출력 -->
	pattern="#.#" : <fmt:formatNumber value="1.23" pattern="#.#" /> <br>		<!-- 1.2 출력 -->
	pattern="#.##" : <fmt:formatNumber value="1.23" pattern="#.##" /> <br>		<!-- 1.23 출력 -->
	pattern="#.###" : <fmt:formatNumber value="1.23" pattern="#.###" /> <br>	<!-- 1.23 출력 패턴 기호 부족. 표시 안 함 -->
	pattern="0" : <fmt:formatNumber value="1.23" pattern="0" /> <br>			<!--1 출력 0도 숫자 하나를 의미 -->
	pattern.="0.0" : <fmt:formatNumber value="1.23" pattern="0.0" /> <br>		<!-- 1.2 출력 -->
	pattern.="0.000" : <fmt:formatNumber value="1.23" pattern="0.000" /> <br>	<!-- 1.230 출력 패턴 기호가 부족해도 0 넣어서 표시 -->


	<!--
		type : number(숫자, 기본값), currency(통화), percent(백분율)
	-->
	숫자 : <fmt:formatNumber value="50000" /> <br>
	숫자 : <fmt:formatNumber type="number" value="50000" /> <br>
	통화 : <fmt:formatNumber type="currency" value="50000" /> <br>					<!-- currency : 통화기호. 직접 미지정시 지역에 맞는 통화기호 출력 -->
	통화 : <fmt:formatNumber type="currency" value="50000" currencySymbol="$"/> <br>	<!-- currencySymbol : 직접 통화기호 지정 -->
	백분율 : <fmt:formatNumber type="percent" value="0.7" /> <br>						<!-- 백분율 70% -->
	

<!-- 230208 3교시 kh-study-cloud/backend 03_EL_JSTL JSTL Formatting Tags -->
	<h2>2.formatDate 태그</h2>
	<p>
		날짜와 시간 데이터의 출력 형식을 지정할 때 사용하는 태그이다.
	</p>
	
	<c:set var="date" value="<%= new Date() %>" />
	
	${ date } <br>
	
	<ul>
		<li>날짜 : <fmt:formatDate value="${ date }" /></li>
		<li>날짜(type="date") : <fmt:formatDate type="date" value="${ date }" /></li>			<!-- type="date" 날짜 정보만, type 속성의 기본값 : date-->
		<li>시간(type="time") : <fmt:formatDate type="time" value="${ date }" /></li>			<!-- type="time" 시간 정보만 -->
		<li>날짜와 시간(type="both") : <fmt:formatDate type="both" value="${ date }" /></li>	<!-- type="both" 날짜, 시간 정보 다 -->
		<li>날짜와 시간(style="short") : <fmt:formatDate type="both" value="${ date }" dateStyle="short" timeStyle="short"/></li>
																				<!-- 날짜 시간 스타일을 짧게 -->
		<li>날짜와 시간(style="medium", 기본값) : <fmt:formatDate type="both" value="${ date }" dateStyle="medium" timeStyle="medium"/></li>
																				<!-- 날짜 시간 스타일을 기본값 -->
		<li>날짜와 시간(style="long") : <fmt:formatDate type="both" value="${ date }" dateStyle="long" timeStyle="long"/></li>
																				<!-- 날짜 시간 스타일을 길게 자세히 -->
		<li>날짜와 시간(style="full") : <fmt:formatDate type="both" value="${ date }" dateStyle="full" timeStyle="full"/></li>
																				<!-- 날짜 시간 스타일을 제일 길고 자세하게 -->
		<li>날짜와 시간(pattern="" 사용자 지정) : <fmt:formatDate type="both" value="${ date }" pattern="yyyy-MM-DD(E) HH:mm:ss(a)"/></li>
																				<!-- pattern="" 사용자가 원하는 패턴 직접 작성 -->		
	</ul>
	
	<!-- 위 28행으로 올라가서 setLocale 태그 보기 -->
	
	

</body>
</html>