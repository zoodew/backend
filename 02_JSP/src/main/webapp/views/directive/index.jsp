<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


<!-- 230203 5교시 02_JSP 4.1 include 지시자-->

<!-- header.jsp 를 include 지시자로 가져옴 -->	
<%@ include file="/views/common/header.jsp" %>
<!-- / webapp의미 > webapp밑 views 밑 common밑 header.jsp 절대 경로로 가져오기 -->
<!-- header.jsp를 가져와서 include 지시자를 작성한 위치시킴 -->

	<section>
		<h2>개인 정보 입력</h2>
		
		<!-- r 230203 5교시 include 지시자 01_Servlet의 webapp views userForm에서 폼태그 가져오기 action 속성 지우고 method속성은 GET으로 변경 -->
		<form action="userFormResult.jsp" method="POST">	<!-- method가 POST이면 영문 숫자만 나오지 나머지는 깨짐 인코딩 설정이 필요함 userFormResult -->
		<!-- 230203 5교시 끝 6교시 ㄴ action 속성 위치 지정에 ./ 같은 것 아무것도 없으면 같은 폴더 내를 의미함. directive 내 userFormResult.jsp로 form 전송시킴-->
			<label for="userName">이름 : </label>
			<input type="text" name="userName" id="userName"></input><br>
			<label for="userAge">나이 : </label>
			<input type="text" name="userAge" id="userAge"></input><br>
			<label><input type="radio" name="gender" value="남자" checked></input>남자</label>
			<label><input type="radio" name="gender" value="여자"></input>여자</label>
		
			<br><br>
			
			좋아하는 음식 :
			<label><input type="checkbox" name="food" value="한식" checked></input>한식</label>	
			<label><input type="checkbox" name="food" value="분식"></input>분식</label>	
			<label><input type="checkbox" name="food" value="중식"></input>중식</label>	
			<label><input type="checkbox" name="food" value="일식"></input>일식</label>	
			
			<br><br>
			
			<input type="submit" value="전송">
			<input type="reset" value="취소">
		</form>
			
	</section>

<!-- footer.jsp 를 include 지시자로 가져옴 -->	
<%@ include file="/views/common/footer.jsp"%>