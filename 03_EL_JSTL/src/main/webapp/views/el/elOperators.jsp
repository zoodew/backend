<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.el.vo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 230207 2교시 kh-study-cloud/backend 03_EL_JSTL EL 연산자-->
	<h1>EL 연산자</h1>
	
	<h2>1. 산술 연산자</h2>
	<!-- 	EL 구문 안에서 산술 연산 가능하다 -->
	10 + 5 = ${ 10 + 5 } <br>
	10 - 5 = ${ 10 - 5 } <br>
	10 * 5 = ${ 10 * 5 } <br>
	<!-- / 또는 div, % 또는 mod -->
	10 / 5 = ${ 10 / 5 } 또는 <%-- ${ 10 div 5 } --%><br>		<!-- 이클립스에서 인식을 못 해서 에러 무시하고 실행해도 됨 값은 잘 나옴 -->	
	10 % 5 = ${ 10 % 7 } 또는 ${ 10 mod 7 }<br>
	
	
	<h2>2. 논리 연산자</h2>
	<!-- && 또는 and, || 또는 or -->
	<!-- &&연산 둘 다 true여야 true, ||연산 둘 중에 하나 true면 true -->
	true && false = ${ true && false } 또는 ${ true and false }<br>
	true || false = ${ true || false } 또는 ${ true or false }<br>
	
	부정연산자(논리값 부정하는 not) :	!(10 > 5) = ${ !(10 > 5) } 또는 ${ not(10 > 5) }
	
	
	<h2>3. 비교 연산자</h2>
	<h3>1) 객체 동등 비교</h3>
	
	<%
	// EL은 영역 객체의(Attribute)에 접근해서 값을 가져오기 떄문에 스크립트릿에 선언된 변수는 가져올 수가 없다. 영역 객체에 값을 저장해야 가져올 수 있다.
		String str1 = "안녕";
		String str2 = new String("안녕");
		/* 두 개의 문자열 생성. 데이터만 같지 주소값은 다름 */
		
		Student student1 = new Student("홍길동", 20, 80, 80);
//		Student student2 = new Student("문인수", 19, 70, 70);
		Student student2 = new Student("홍길동", 20, 80, 80);
		
		System.out.println(str1 == str2);
			// 거짓. == 비교는 데이터의 주소값 비교함. 데이터의 주소값이 다르기 때문에 거짓
		System.out.println(str1.equals(str2));
			// 참. 문자열 비교는 문자열 동등비교 메소드인 .equals() 사용해야 함.
	
	// Page 영역에 데이터를 저장
		pageContext.setAttribute("str1", str1);
		pageContext.setAttribute("str2", str2);
							// setAttribute(어트리뷰트의 이름, 담길 값(위의 str1 변수))
		pageContext.setAttribute("student1", student1);
		pageContext.setAttribute("student2", student2);
	%>
	
	<%--
		<!-- el은 영역객체 안에 있는 값을 가져옴 스크립트 안에 선언된 변수는 가져올 수가 없음. 그래서 값을 영역객체에 넣어줌 50행없으면 공란으로 나옴-->
	str1 : ${ str1 } <br>
	str2 : ${ str2 } <br>
	
	<!-- Student.java에서 어노테이션 @ToString @EqualsAndHashCode 추가 배열값 제대로 나오게  -->
	student1 : ${ student1 } <br>
	student2 : ${ student2 } <br>
	--%>
	
	<table border="1">
		<tr>
			<th>비교식</th>
			<th>표현식 태그</th>
			<th>EL</th>
		</tr>
		<tr>
			<td>str1 == str2</td>
			<td><%= str1 == str2 %></td>					 <!-- 표현식 태그 안의 비교 연산은 참조변수의 주소값을 비교함. false 나옴 -->
			<td>${ str1 == str2 } 또는 ${ str1 eq str2 }</td>	 <!-- EL 구문의 비교연산(==)은 equals()와 같은 동작을 한다. EL에서 ==는 eq라고도 쓴다. 문자열 비교 true-->
		</tr>
		<tr>
			<td>str1 != str2</td>
			<td><%= str1 != str2 %></td>
			<td>${ str1 != str2 } 또는 ${ str1 ne str2 }</td>	 <!-- EL에서 !=는 ne라고도 쓴다 -->
		</tr>
		<tr>
			<td>student1 == student2</td>
			<td><%= student1 == student2 %></td>								<!-- 주소값 다름 > false -->
			<td>${ student1 == student2 } 또는 ${ student1 eq student2 }</td>		<!-- 문자열 다름 > false 43행을 42행 데이터값과 같게 만들면 true 나옴-->
		</tr>
		<tr>
			<td>student1 != student2</td>
			<td><%= student1 != student2 %></td>								<!-- 주소값 다름 > true -->
			<td>${ student1 != student2 } 또는 ${ student1 ne student2 }</td>		<!-- 문자열 다름 > true 43행을 42행 데이터값과 같게 만들면 false 나옴 -->
		</tr>
	</table>
	
<!-- 230207 3,4교시 kh-study-cloud/backend 03_EL_JSTL EL 연산자-->
	<h3>2) 숫자 비교 연산</h3>
	
	<%
	// Page 영역에 데이터를 저장 pageContext에 숫자값 두 개 넣음
		pageContext.setAttribute("big", 10);
//		pageContext.setAttribute("big", "10");		// big의 데이터값을 문자열로 바꾸면 표현식 태그는 에러가 나고(표현식 태그 주석 달고 확인) EL은 에러가 나지 않음 "10"을 숫자로 바꿔줌
		pageContext.setAttribute("small", 3);

	%>
	
	<!-- 표현식 태그 -->
		<%-- 표현식 태그 : <%= pageContext.getAttribute("big") + pageContext.getAttribute("small") %>
		표현식 태그 : <%= pageContext.getAttribute("big") > pageContext.getAttribute("small") %> --%>
		<!--
			에러 발생. 이유?? > getAttribute로 받는 타입 = object. object 타입끼리는 비교연산 산술연산이 불가능하다. 형변환을 해줘야 함
			 integer 타입으로 형변환 함. integer타입끼리는 왜 연산이 되나요? 나중에 boxing으로 형변환 될 거라서.
		-->
	표현식 태그 : <%= (Integer)pageContext.getAttribute("big") + (Integer)pageContext.getAttribute("small") %> <br>
	표현식 태그 : <%= (Integer)pageContext.getAttribute("big") > (Integer)pageContext.getAttribute("small") %>
	
	<br><br>
	
	<!-- EL 구문 -->
	<!-- EL은 자동형변환을 해서 굳이 직접 형변환해주지 않아도 됨. 더 간결하게 접근이 가능함. -->
	EL : ${ big + small } <br>
	EL : ${ big > small } <br><br>

	big > small : ${ big > small } 또는 ${ big gt small } <br>
	big < small : ${ big < small } 또는 ${ big lt small } <br><br>
	<!-- ㄴ 직접 기호를 넣으면 태그로 인식을 하는 경우가 있어서 엔티티 기호로 표현 -->
	big &gt; small : ${ big > small } 또는 ${ big gt small } <br>
	big &lt; small : ${ big < small } 또는 ${ big lt small } <br>
	big &ge; small : ${ big >= small } 또는 ${ big ge small } <br>
	big &le; small : ${ big <= small } 또는 ${ big le small } <br>
	
	
	
	<h3>3) 객체가 null 또는 비어있는지 체크하는 연산자</h3>
	<%
		// 문자열 생성
		String str3 = null;						// str3 문자열에 참조변수로 null 들어감
		//List<String> list = null;				// list 리스트에 참조변수로 null 들어감
		List<String> list = new ArrayList<>();	// list는 List라는 요소를 갖고는 있지만 그 객체가 비어있는 객체
		
		System.out.println(list.isEmpty());
		
		pageContext.setAttribute("str3", str3);
		pageContext.setAttribute("list", list);
	%>
	
	표현식 태그 : <%= str3 == null %>, <%= list.isEmpty() %><br><br>
	
	EL : ${ str3 == null }, ${ empty str3 } <br>		<!-- true true -->
	EL : ${ list == null }, ${ empty list } <br><br>	<!-- false true --> <!-- list는 List라는 요소를 갖고는 있지만 그 객체가 비어있는 객체. 앞은 false, 뒤는 true가 나옴 -->

	EL : ${ str3 != null }, ${ not empty str3 } <br>	<!-- true true -->
	EL : ${ list != null }, ${ not empty list } <br>	<!-- false true -->
 		<!-- ==는 단순히 null인지만 체크, empty는 null 체크랑 요소가 비어있는지(isEmpty()) 한 번에 가능 -->
					
	

</body>
</html>