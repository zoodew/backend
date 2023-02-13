<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>
	section #enroll-container {
		text-align:center;
	}
	
	section #enroll-container input {
		margin:3px;
	}
	
	section #enroll-container table {
		margin:0 auto;
	}
	
	section #enroll-container table th {
		padding:0 10px; 
		text-align:right;
	}
	
	section #enroll-container table td {
		padding:0 10px; 
		text-align:left;
	}
</style>

										<!-- 230210 4교시 회원가입 기능 구현 -->

<section id="content">
	<h2 align="center">회원 가입 정보</h2>
	<div id="enroll-container">	 	
	 	<form name="memberEnrollFrm" action="${ path }/member/enroll" method="post">
	 		<table>
	 			<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="newId" placeholder="아이디(4글자이상)" required>
						<input type="button" id="checkDuplicate" value="중복검사" >
					</td> 			
	 			</tr>
	 			<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="userPwd" id="pass1" required>
					</td> 			
	 			</tr>
	 			<tr>
					<th>패스워드확인</th>
					<td>
						<input type="password" id="pass2">
					</td> 			
	 			</tr>
	 			<tr>
					<th>이름</th>
					<td>
						<input type="text" name="userName" id="userName" required>				
					</td> 			
	 			</tr>
	 			<tr>
					<th>휴대폰</th>
					<td>
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11">								
					</td> 			
	 			</tr>
	 			<tr>
					<th>이메일</th>
					<td>
						<input type="email" placeholder="abc@abc.com" name="email" id="email">												
					</td> 			
	 			</tr>
	 			<tr>
					<th>주소</th>
					<td>
						<input type="text" name="address" id="address">
					</td> 			
	 			</tr>
	 			<tr>
					<th>취미</th>
					<td>
						<label><input type="checkbox" name="hobby" id="hobby0" value="운동">운동</label>
						<label><input type="checkbox" name="hobby" id="hobby1" value="등산">등산</label>
						<label><input type="checkbox" name="hobby" id="hobby2" value="독서">독서</label>
						<label><input type="checkbox" name="hobby" id="hobby3" value="게임">게임</label>
						<label><input type="checkbox" name="hobby" id="hobby4" value="여행">여행</label>
					</td> 			
	 			</tr> 		
	 		</table> 
	 		<input type="submit" id="enrollSubmit" value="가입">	
	 		<input type="reset" value="취소">	
	 	</form>
 	</div>
</section>

<script>

/* 230213 1교시 취미 체크박스 선택 안 하고 가입시 null point exception 뜨는 것 해결 */
	
	// 아이디 중복 확인
	$(document).ready(() => {
		$('#checkDuplicate').on('click', () => {
			let userId = $('#newId').val().trim();
				// newId가 아이디인 요소를 가져와 val()로 newId의 값을 가져오고 trim()으로 공백 제거
				
			// ajax 요청
			$.ajax({
				type: 'POST',
				url: '${ path }/member/idCheck',
				dataType: 'json',
				data: {
					userId
				},
				success: (obj) => {	// json (230213 2교시에서 gson으로 Object객체로 바꿔서 data에서 obj로 변경)
					console.log(obj);
				
					if(obj.duplicate) {
						alert("이미 사용중인 아이디 입니다.");
					} else {
						alert("사용 가능한 아이디 입니다.");
					}
				},
				error: (error) => {
					console.log(error);
				}	
			});
		});
		
		
		
	});


</script>


















<jsp:include page="/views/common/footer.jsp" /> 