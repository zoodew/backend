<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ path }/resources/css/style.css">
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
	<header>
		<h1>Hello MVC</h1>
		
		<%-- ${ empty loginMember } <br> --%>
		<!-- 영역객체 안에 loginMember가 있는 지 확인
			true > null. 로그인 안 됐을 때. false 로그인 됐을 때.
			
			한 번 로그인 하면 새로고침을 계속 해도 계속 false 떠 있음 왜냐???
			loginMember 세션에 저장
				//	session은 서버가 클라이언트에 대한 정보를 가지고 있는 영역. 하나의 브라우저에 접속하면 그 브라우저가 닫힐 때까지 유효 
			
			로그인 됐을 때 로그인 폼 안 보이게 만들기-->
			
		<div class="login-container">
			
			<!-- 로그인이 안 되어있을 때 로그인 폼 보여달라는 코드 -->
			<c:if test="${ empty loginMember }">
				<form id="loginFrm" action="${ path }/login" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디"
									value="${ empty cookie.saveId ? '' : cookie.saveId.value }" required>
									<!-- ㄴ 230210 3교시 아이디 저장기능 구현 -->
									<!-- 쿠키가 empty라면 아무것도 안 하고 : 쿠키가 존재하면 아이디값을 계속 보여주게 설정 -->
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" required>
							</td>
							<td>
								<input type="submit" value="로그인">						
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label><input type="checkbox" name="saveId"
											${ empty cookie.saveId ? "" : "checked" }>아이디 저장</label>
											<!-- ㄴ 230210 3교시 아이디 저장기능 구현 -->
											<!-- 쿠키가 empty라면 아무것도 안 하고 : 쿠키가 존재하면 아이디 저장 체크박스가 체크되어있게 설정 -->
								<input type="button" value="회원가입" onclick="location.href = '${ path }/member/enroll';"> 
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			
			<!-- 로그인 됐을 때 뜨는 테이블 -->
			<c:if test="${ not empty loginMember }">
				<table>
					<tr>
						<td colspan="2">
							${ loginMember.name }님 안녕하세요.
						</td>
					</tr>
					<tr>
						<td>
<!-- 230213 2교시 내 정보 버튼 클릭하면 사용자 정보 보이게 -->
							<button onclick="location.href='${ path }/member/myPage'">내 정보</button>
						</td>
						<td>
							<button onclick="location.replace('${ path }/logout')">로그아웃</button>
						</td>
					</tr>
				</table>
			</c:if>
			<!-- 로그아웃 버튼까지만 누르고 기능 구현은 안 했을 때 제대로 작동되나 확인하는 법! 개발자도구의 애플리케이션탭 쿠기의 로그 삭제 새로고침 -->
		
		
		
		</div>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="${ path }/">Home</a></li>
<!-- 230213 7교시 게시판 구현 -->
				<li id="board"><a href="${ path }/board/list">게시판</a></li>
				<li id="admin-member"><a href="${ path }/">회원관리</a></li>
			</ul>
		</nav>
	</header> 