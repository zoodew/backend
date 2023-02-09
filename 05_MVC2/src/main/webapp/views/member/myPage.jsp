<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<form id="memberFrm" action="${ path }/member/update" method="post">
			<table>
				<tr>
	                <th>아이디</th>
					<td>
						<input type="text" name="userId" id="newId" readonly required >
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
	        <button type="button">비밀번호변경</button>
	        <input type="submit" value="정보수정">
	        <input type="button" value="탈퇴">
	 	</form>
 	</div>
</section>

<jsp:include page="/views/common/footer.jsp" /> 