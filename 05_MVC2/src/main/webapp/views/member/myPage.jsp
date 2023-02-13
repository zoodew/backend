<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>
	section #view-container {
		text-align:center;
	}
	
	section #view-container input {
		margin:3px;
	}
	
	section #view-container table {
		margin:0 auto;
	}
	
	section #view-container table th {
		padding:0 10px; 
		text-align:right;
	}
	
	section #view-container table td {
		padding:0 10px; 
		text-align:left;
	}
</style>
<section id="content">
	<h2 align="center">회원 정보 수정</h2>
	<div id="view-container">
		<form id="memberFrm" action="${ path }/member/update" method="POST">
			<table>
				<tr>
	                <th>아이디</th>
					<td>
<!-- 230213 2교시 내 정보 버튼 클릭하면 사용자 정보 보이게 value="${ loginMember.id }"...-->
						<input type="text" name="userId" id="newId" 
							value="${ loginMember.id }" readonly required >
					</td> 	
	            </tr>
	            <tr>
	                <th>이름</th>
					<td>
						<input type="text" name="userName" id="userName"
							value="${ loginMember.name }" required>				
					</td> 	
	            </tr>
      	        <tr>
	                <th>휴대폰</th>
	                <td>
	                    <input type="tel" placeholder="(-없이)01012345678" name="phone"
	                    	value="${ loginMember.phone }" id="phone" maxlength="11">
	                </td>
	            </tr>
	            <tr>
	                <th>이메일</th>
					<td>
						<input type="email" placeholder="abc@abc.com" name="email" id="email"
						value="${ loginMember.email }">												
					</td> 	
	            </tr>
	            <tr>
	                <th>주소</th>
						<td>
							<input type="text" name="address" id="address"
								value="${ loginMember.address }">
						</td> 	
	            </tr>
	            <tr>
<!-- 230213 3교시 내 정보 버튼 클릭하면 사용자 정보 보이게 -->
	               	<th>취미</th>
					<td>
						<label><input type="checkbox" name="hobby" id="hobby0" value="운동"
									${ fn:contains(loginMember.hobby, '운동') ? 'checked' : '' }>운동</label>
									<!-- loginMember의hobby값에 운동이 있으면 checked, 없으면 빈문자열 확인해줘 -->
						<label><input type="checkbox" name="hobby" id="hobby1" value="등산"
									${ fn:contains(loginMember.hobby, '등산') ? 'checked' : '' }>등산</label>
						<label><input type="checkbox" name="hobby" id="hobby2" value="독서"
									${ fn:contains(loginMember.hobby, '독서') ? 'checked' : '' }>독서</label>
						<label><input type="checkbox" name="hobby" id="hobby3" value="게임"
									${ fn:contains(loginMember.hobby, '게임') ? 'checked' : '' }>게임</label>
						<label><input type="checkbox" name="hobby" id="hobby4" value="여행"
									${ fn:contains(loginMember.hobby, '여행') ? 'checked' : '' }>여행</label>
					</td> 		
	            </tr>
	        </table>
<!-- 230213 4교시 비밀번호 변경하기 -->
	        <button type="button" id="btnUpdatePwd">비밀번호변경</button>
	        <input type="submit" value="정보수정">
<!-- 230213 6교시 회원정보 수정 탈퇴하기 -->
	        <input type="button" id="btnDelete" value="탈퇴">
	 	</form>
 	</div>
</section>
<script>

	$(document).ready(() => {
<!-- 230213 4교시 비밀번호 변경하기 -->
		$('#btnUpdatePwd').on('click', () => {
			let url = '${ path }/member/updatePwd';
			let status = 'left=500px, top=200px, width=500px, height=250px';	// 새로 띄우는 창 크기랑 위치 설정
		
			open(url, 'updatePwd', status);
		
		});
	
<!-- 230213 6교시 회원정보 수정 탈퇴하기 -->		
		$('#btnDelete').on('click', () => {
			if(confirm('정말로 탈퇴하시겠습니까?')) {
				location.replace('${ path }/member/delete');
			}
			
		});
		
	
	
	});

	
	
	
	
	
	
	
	
	
	
</script>

<jsp:include page="/views/common/footer.jsp" /> 