<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 스크립트 요소</title>
</head>
<body>

<!-- 230203 2교시 backend 02_JSP -->
	<h1>JSP 스크립트 요소</h1>
	
	<!-- HTML 주석 -->
	<!-- 주석 단축키 : ctrl shift / -->
	<%-- JSP 주석 --%>
	
	<%--
		HTML JSP 두 주석의 차이점은
		페이지 소스 보기 혹은 개발자 도구에서 HTML 주석은 확인이 가능하고 JSP 주석은 확인이 불가능하다.

		JSP 파일에서 JSP주석은 서블릿으로 변환될 때 포함이 되지 않음.
		HTML 주석은 포함이 됨.
		실제로 소스 보기를 했을 때 HTML 주석은 보이나 JSP 주석은 보이지 않음 문서에서만 확인이 가능
	--%>
	
	
	<%-- 선언문 태그 --%>
	<!-- 선언문 태그 안에 선언을 하면 서블릿 클래스(index_jsp.java)에 필드나 메소드가 생성 -->
	<%!
		private String name = "문인수";
	
		public String getName() {
			return this.name;
		}
	%>
	
	
	<%-- 스크립트릿 태그 --%>
	<!-- 
		_jspService() 메소드의 로컬(지역) 변수와 자바 코드를 작성할 때 사용한다
		스크립트릿 태그 안에 식을 작성하면 서블릿 클래스(index_jsp.java)에 자바 코드 작성
	-->
	<%
		int sum = 0;
		
		for(int i = 1; i <= 10; i++) {
			sum += i;
			
	%>
	<h2>안녕하세요.</h2>
		<!-- for 문을 중간에 태그로 분리해 그 사이에 식을 포함하는 것도 가능함-->
	<%
		}

		System.out.println(sum);
	%>
	
		
	<%-- 표현식 태그 --%>
	<!-- 
		서블릿 코드에서 out.print()의 역할 수행하는 태그로 클라이언트로 데이터를 출력하는 코드를 작성할 때 사용한다
	-->
		<!-- 스크립트 릿 태그로 추가. out.print()는 자바 코드라서 ; 써 줌 -->
		저의 이름은 <% out.print(name); %> <br>
	<!-- 표현식 태그로 추가
		out.print(name) 으로 들어감 그래서 name 옆에 ; 쓰지 않음 쓰면 out.print(name;)과 동일-->
	저의 이름은 <%= name %> <br><br>
	
	표현식 태그가 스크립트릿 태그보다 더 간결하게 작성될 뿐 실제로는 서블릿으로 변환된 같은 코드이다. <br><br>
	
	1부터 10까지의 합은 <% out.print(sum); %> <br>
	1부터 10까지의 합은 <%= sum %>

	
</body>
</html>