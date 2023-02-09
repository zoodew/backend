<%@page import="com.kh.el.vo.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 230207 6교시 kh-study-cloud/backend 03_EL_JSTL JSTL Core Tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
	태그라이브러리 지시자 선언
	uri		: 코어태그라이브러리를 사용한다고 식별자처럼 적어주는 것 구분자라고 생각하기
	prefix	: 코어태그라이브러리에서 주는 태그라이브러리 사용할 때 접두어 뭘로 쓸지 지정
-->
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230207 6교시 kh-study-cloud/backend 03_EL_JSTL JSTL Core Tags -->
	<h1>JSPL Core Library</h1>
	
	<h2>1. 변수</h2>
	
	<h3>1) 변수 선언</h3>
	<p>
		변수를 선언하고 초기값을 대입하는 태그이다. <br>
		선언된 변수는 EL구문을 통해 사용이 가능 스크립트릿으로는 사용 불가능.
	</p>

	
	<!-- 변수 선언 -->
	<c:set var="num1" value="10" />
	<%-- <%pageContext.setAttribute("num1", "10");%> --%>	<!-- set이라는 코어태그를 가지고 변수를 만들어내서 사용하는 위의 코드가 이 코드와 동일 -->
	<c:set var="num2" value="20" scope="request" />
								<!-- ㄴ 변수의 scope 지정 -->
	<%-- <%pageContext.setAttribute("num1", "20");%> --%>	<!-- set이라는 코어태그를 가지고 변수를 만들어내서 사용하는 위의 코드가 이 코드와 동일 -->
	<c:set var="result" value="${ num1 + num2 }" scope="session" />
	
<!-- 230207 7교시 kh-study-cloud/backend 03_EL_JSTL JSTL(JSP Standard Tag Library) -->
	<!-- 배열 생성 
		여는 태그와 닫는 태그 사이에 값을 넣어주면 배열이 생성됨-->
	<c:set var="array" scope="application">
		red, blue, yellow, pink, green
	</c:set>

<!-- 230207 6교시 kh-study-cloud/backend 03_EL_JSTL JSTL Core Tags -->	
	<!-- EL 구문으로 사용 -->
	num1 변수의 값 : ${ num1 } <br>
	num2 변수의 값 : ${ num2 } 또는 ${ requestScope.num2 } 또는 <%= request.getAttribute("num2") %> <br>
								<!-- requestScope를 통해 접근해서 num2의 scope를 확인할 수 있다. -->
	result 변수의 값 : ${ result } 또는 ${ sessionScope.result } <br>
								<!-- sessionScope를 통해 접근해서 result의 scope를 확인할 수 있다. -->
								
<!-- 230207 7교시 kh-study-cloud/backend 03_EL_JSTL JSTL Core Tags -->
	array 배열의 값 : ${ array } <br>



	<h3>2) 변수 삭제</h3>
	<p>
		c:set 태그로 선언한 변수를 삭제할 때 사용하는 태그이다.
	</p>

	<!-- 동일한 이름으로 세 군데의 영역(scope)에 저장해 둠
	36행 result 변수 session 스코프에 저장 -->
	<!-- (기본적으로) scope 지정 안 할 시 page 스코프에 저장됨 --> 
	<c:set  var="result" value="99999"/>
	<!-- request 스코프에 저장 -->
	<c:set  var="result" value="10000" scope="request"/>
	
	삭제 전 : ${ result }	<br><br>	<!-- page 스코프에 저장된 result 변수의 값인 99999 출력 -->
	
	
	<c:remove var="result" scope="page"/>
	
	삭제 후 : ${ result }	<br><br>	<!-- request 스코프에 저장된 result 변수의 값인 10000 출력 -->
	
	<c:remove var="result" /> 
	<!-- 별도로 scope를 지정하지 않으면 모든 scope(영역)에 있는 변수가 삭제됨 -->
	
	모든 scope 삭제 후 : ${ result }		<!-- 아무 것도 출력되지 않음 -->
	
	
	
	<h2> 2. 출력</h2>
	<p>
		클라이언트로 데이터 출력할 때 사용하는 태그이다.
	</p>
	
	<!-- c:out 태그 value 속성에 출력할 데이터를 주면 됨 -->
	태그를 문자열로 출력(escapeXml 미지정) : <c:out value="<b>태그로 출력하기</b>" /> <br>	 <!-- 기본적으로는 태그를 문자열로 인식해서 출력함 escapeXml="true"이 기본값 -->
	태그를 문자열로 출력(escapeXml="true") : <c:out value="<b>태그로 출력하기</b>" escapeXml="true" /> <br>	 <!-- 기본적으로는 태그를 문자열로 인식해서 출력함 escapeXml="true" -->
	태그를 태그로 해석해서 출력(escapeXml="false)"  : <c:out value="<b>태그로 출력하기</b>" escapeXml="false" /> <br>
	
	기본값 출력 : <c:out value="${ result }" default="값이 없음" />	<!-- EL에 값이 없는 경우에 default에 지정된 값이 출력됨 -->

	
	
	<h2>3. 조건문</h2>
	<h3>1) c:if 태그</h3>
	<p>
		자바의 if 구문과 같은 역할을 하는 태그이다. <br>
		조건식은 test 속성에 EL 구문으로 기술해야 한다. <br>
		조건식의 결과가 참일 때 c:if> ~ /c:if> 사이에 있는 내용을 처리한다. <br>
		조건을 만족하는 경우에만 c:if 태그 사이의 내용을 처리함. 조건을 만족하지 않는 경우는 내용을 처리하지 않음
	</p>
	
	<!-- 30행에서 선언한 변수(num1, num2)를 사용 -->
	
	<c:if test="${ num1 > num2 }" >
		<b>num1이 num2보다 크다.</b>
	</c:if>

	<c:if test="${ num1 < num2 }" >
		<b>num1이 num2보다 작다.</b>
	</c:if>

		<%--
			c:if 를 사용하면 스크립트릿 태그를 사용해서 만드는 것보다 간단하게 조건문 작성이 가능함 여러 언어를 사용하면 복잡해지니까 간단하게 보기 쉽게 표현하는 것이 중요.
			<%
				if( 10 >20 ){
			%>
				<b>10이 20보다 작다.</b>
			<%		
				}
			%>
		--%>
	
	
	<h3>2) c:choose 태그</h3>	
	<p>
		자바의 switch 구문과 같은 역할을 하는 태그이다. <br>
		하위 태그인 c:when, c:otherwise 태그와 함께 사용되는데, 각각 switch 구문의 case, default 절과 비슷한 역할을 한다.
	</p>
	
	<!-- 30행에서 선언한 변수(num1, num2)를 사용 -->
	
	<c:choose>
		<c:when test="${ num1 > num2 }">
			<b>num1이 num2보다 크다.</b>		
		</c:when>
		
		<c:when test="${ num1 < num2 }">
			<b>num1이 num2보다 작다.</b>
		</c:when>
	
		<c:otherwise>					<!-- 앞의 조건들을 다 만족하지 않을 경우 -->
			<b>num1과 num2가 같다.</b>
		</c:otherwise>
	
	</c:choose>	
	
	<!--
		c:if와 c:choose 차이 
			if는 첫 번째 조건이 만족해도 그 다음 조건을 또 봄
			choose는 첫 번째 조건이 만족하면 그 다음 조건을 안 봄
			
			num1 num2의 값을 바꿔가변서 테스트 해보기
	-->
	
	
	
	<h2>4. 반복문</h2>
	
	<h3>1) c:forEach 태그</h3>
	<p>
		자바의 for 구문에 해당하는 역할을 하는 태그이다.
	</p>
	
	<h4>자바의 for 구문처럼 사용하기</h4>

	<c:forEach var="i" begin="1" end="6">
			<!-- i라는 변수 선언, 1부터 6까지 실행 -->
		${ i } <br>										<!-- 1 2 3 4 5 6 출력 -->
	</c:forEach>
									<!-- step : 증가값. step 값은 반드시 0보다 커야 한다.  -->
	<c:forEach var="i" begin="1" end="6" step="2">
		<!-- 태그 안에도 EL 적용 가능 -->
		<h${ i }>반복 확인 : ${ i }</h${ i }>
	</c:forEach>
	

<!-- 230207 8교시 kh-study-cloud/backend 03_EL_JSTL JSTL Core Tags -->
	<h4>자바의 향상된 for 구문처럼 사용하기</h4>
	
	<!-- 41행 array 배열 사용하기 -->
	<c:forEach var="color" items="${ array }">
			<!-- ㄴ items 속성에 담긴 배열의 요소 갯수만큼 반복하면서 color라는 변수에 값을 담아줌 -->
		<span style="color: ${ color };">배열 확인 : ${ color }</span> <br>	<!-- span 태그에 담아서 스타일 지정해 줄 수도 있다. -->	
	</c:forEach>
	
	<%
		List<Student> list = new ArrayList<>();
		
		list.add(new Student("문인수", 19, 80, 80));
		list.add(new Student("홍길동", 30, 70, 90));
		list.add(new Student("이몽룡", 29, 70, 70));
		list.add(new Student("김철수", 26, 100, 100));
		
		pageContext.setAttribute("list", list);
	%>
	
	<h5>학생 목록 조회</h5>
	
	<table border="1">
		<tr>
			<th>인덱스</th>
			<th>순번</th>
			<th>이름</th>
			<th>나이</th>
			<th>수학 점수</th>
			<th>영어 점수</th>
			<th>First</th>
			<th>Last</th>
		</tr>									<!-- varStatus : 현재 반복에 해당하는 객체 요소. 현재 반복의 상태값을 가지고 있음 인덱스값, 첫번째반복진지,...-->
		<c:forEach var="student" items="${ list }" varStatus="status">
						<!-- list에 담긴 값들을 하나씩 student 변수에 담아서 반복해 출력 -->
			<tr>
				<td>${ status.index }</td>	<!-- 인덱스 번호 갖고 옴 0부터 -->
				<td>${ status.count }</td>	<!-- 순서 가져욤 1부터 -->
				<td>${ student.name }</td>
				<td>${ student.age }</td>
				<td>${ student.math }</td>
				<td>${ student.english }</td>
				<td>${ status.first }</td>	<!-- 첫 번째 반복인지 첫 번째라면 true 출력 -->
				<td>${ status.last }</td>	<!-- 마지막 반복인지 마지막이라면 true 출력 -->
			</tr>
		</c:forEach>
		
		<%--
		이렇게 스크립트릿으로 만들어 내는 것을 c:forEach 태그 형태로 만들어서 사용
		<%
			for(Student s : list) {
		%>
			
		<%
			}
		%>
		--%>
	</table>
	
	
	
<!-- 230208 1교시 kh-study-cloud/backend 03_EL_JSTL JSTL Core Tags -->

	<h3>2) c:forTokens 태그</h3>
	<p>
		문자열에 포함된 구분자를 통해 토큰을 분리해 반복을 수행하는 태그이다.
	</p>	
	
	<ul>		<!-- var 속성에 변수명. 받아온 items를 var변수에 넣어줌 delims : 구분자. 구분자는 여러 개 넣을 수 있음. 여기서는 공백, 콤마(,), 슬래시(/) -->
		<c:forTokens var="color" items="pink red tomato,lime/yellowgreen aqua" delims=" ,/">
			<li style="color:${ color }">${ color }</li>
		</c:forTokens>
	</ul>


	<h3>2) c:url 태그</h3>
	<p>
		URL을 생성하고 쿼리 스트링을 미리 설정하는 태그이다. <br>
		
		URL을 미리 만들어 놓고 필요한 곳에서 EL 태그로 접근할 수 있게 만들어 놓은 태그
	</p>
			<!-- var 변수에 value를 값으로 넣음 -->
	<c:url var="url" value="/views/el/elParam.jsp">	<!-- '/' = webapp 뜻함 -->
		<c:param name="pName" value="아이폰 12 미니"/>		<!-- 쿼리스트링. URL에 값이 뜨고(GET 방식) 출력이 됨 -->
		<c:param name="pCount" value="2"/>
		<c:param name="option" value="화이트"/>
		<c:param name="option" value="128GB"/>
	</c:url>			
			<!-- r 개발자도구에서 요소를 확인하면 /03_EL_JSTL/views/el/elParam.jsp로 나온다. -->
	<a href="${ url }">이동</a>
	


	
</body>		
</html>