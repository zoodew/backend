<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 230208 7교시 jQuery 가져오기 -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<title>AJAX(Asynchronous Javascript And XML)</title>
</head>
<body>

<!-- 230208 4교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf -->
	<h1>AJAX(Asynchronous Javascript And XML)</h1>
	
	<h2>1. Javascript를 이용한 AJAX 테스트</h2>
	<h3>1) GET 방식으로 서버에 데이터 전송 및 응답</h3>
	<!-- AJAX 때는 보낼 방식을 지정할 수 있다. GET으로 보낼지 POST로 보낼지 -->
	
	<button onclick="jsAjaxTest1();">GET 방식 전송</button>
		<!-- 버튼 클릭시 jsAjaxTest1(); 함수 작동. script 태그 내에 함수 생성 -->
	
	<!-- p 태그에 서버 실행시 전달받은 값을 입력시킴 230208 6교시-->
	<p id="p1"></p>
	
	
	<script type="text/javascript">
		function jsAjaxTest1() {
		
		// 1. XMLHttpRequest 객체 생성
			let xhr = new XMLHttpRequest();
						
			/*
			ie 6 이하에서는 필요함. (참고)
			// 구버전을 위한 호환성 코드입니다. 더 이상 이렇게 작성하지 않아도 됩니다.
			if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
			    xhr = new XMLHttpRequest();
			} else if (window.ActiveXObject) { // IE 6 이하
			    xhr = new ActiveXObject("Microsoft.XMLHTTP");
			} else {
				xhr = null
			}
			*/
			

<!-- 230208 5교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf -->
		
		// 2. onreadystatechange 속성에 콜백 함수 지정.
			//onreadystatechange 서버 응답 상태가 변경될 때마다 실행되는 메소드. 그 때마다 콜백함수 호출
			xhr.onreadystatechange = function() {
			
				// 1) 서버 응답 상태 확인
					// console.log 해서 콜솔에 찍히는 값이
					//  readyState > 0 : 요청이 초기화되지 않은 상태
					//  readyState > 1 : 서버와 연결이 설정된 상태
					//  readyState > 2 : 서버가 요청을 받은 상태
					//  readyState > 3 : 서버가 요청을 처리하는 상태
					//  readyState > 4 : 서버가 요청에 대한 처리를 끝내고 응답을 준비하는 상태
					
				console.log('readyState: ' + xhr.readyState);
				
				if(xhr.readyState === 4) {		// 서버 응답 상태가 4번이면 아래 코드 실행
					
				// 2) HTTP 응답 상태 코드 확인
					// 200 : OK
					// 404 : Not Found
					// 500 : Internal Server Error
					// ...
					
					console.log('status' + xhr.status);
						/* 3.의 xhr.open('GET', "${ pageContext.request.contextPath}/jsAjax.do", true);
						가운데 요청 보낼 서버 url에 잘못된 경로 적은 후 확인해보기. console에 404 뜸*/
					
					if (xhr.status === 200) {
						
						// responseText 서버에서 응답한 데이터를 담고있는 속성
						console.log(xhr.responseText);
						
						// p 태그에 서버 실행시 전달받은 값을 입력시킴 230208 6교시
						document.getElementById("p1").innerHTML = xhr.responseText;
																// reponseText 서버에서 응답한 데이터를 담고있는 속성
					} else {
						console.log('통신 실패 : ' + xhr.status);
					}
						
					
				} 
			};
			
		// 3. open() 메소드 호출
			/* 서버와 통신에 필요한 정보들 입력 (요청 방식, 요청 보낼 서버 URL, 비동기 여부[true 비동기])*/
			xhr.open('GET', "${ pageContext.request.contextPath}/jsAjax.do?name=문인수&age=19", true);
					// GET 방식: 데이터를 URL에 담아서 보냄 문인수 19
					
		
		// 4. send() 메소드 호출
			/* 위 open을 통해 받아온 정보들을 담아 실제로 서버에 요청하는 메소드 */
			xhr.send();
				/* 개발자 도구에 들어가서 콘솔 보면 get방식 전송을 눌렀을 때 404에러가 발생함. 보내주는 곳이 없어서.
					main/java에 JavascriptAjax 서블릿 만들어서 매핑 /jsAjax.do로 설정해줌
					서블릿 만들면 서버 다시 시작해야 에러가 사라짐*/
		
		}
		
		
	</script>


	
<!-- 230208 6교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf -->
	<h3>2) POST 방식으로 서버에 데이터 전송 및 응답</h3>
	
	<button onclick="jsAjaxTest2();">POST 방식 전송</button>
	
	<p id="p2"></p>
	
	
	<script>
		function jsAjaxTest2() {
			
		// 1. XMLHttpRequest 객체 생성
			let xhr = new XMLHttpRequest();
			
		// 2. onreadystatechange 속성에 콜백 함수 지정. 서버 응답 상태가 변경되면 호춛되는 콜백함수
			xhr.onreadystatechange = () => {	/* arrow function 형태 */
				console.log(xhr.readyState);
				if (xhr.readyState === XMLHttpRequest.DONE){	// XMLHttpRequest에서 상수를 제공함. DONE이라고 하면 응답 준비 완료 상태라는 의미  
					if(xhr.status === 200) {
						console.log('통신 성공' + xhr.status)
						
						// p2 에 출력되도록 만들기
						document.getElementById('p2').innerHTML = xhr.responseText;
																	// responseText 서버에서 응답한 데이터를 담고있는 속성
					} else {
						console.log('통신 실패' + xhr.status);
					}
				}			
			};
		
			
		// 3. open() 호출
			/* 서버와 통신에 필요한 정보들 입력 (요청 방식, 요청 보낼 서버 URL, 비동기 여부)*/
			xhr.open('POST', '${ pageContext.request.contextPath}/jsAjax.do', true);
			
			// *POST 요청의 경우 send() 호출 전에 아래와 같이 요청 헤더를 추가 수정해야 한다.
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');			
				/* JavascriptAjaxServlet.java의 42행을 추가해서 한글이 깨지지 않게 인코딩 시켜줌 이 대신 ㄴ 위처럼 헤더 추가수정 charset=UTF-8.때 UTF-8 인코딩도 가능
					별도로 서버에서 인코딩 수정 할 필요 없이 인코딩 가능함. 둘 중에 하나의 방법으로 사용하면 됨.*/
		// 4. send() 메소드 호출
			/* 위 open을 통해 받아온 정보들을 담아 실제로 서버에 요청하는 메소드 */
			
			/* POST는 데이터를 URL에 담아서 보내(GET방식)지 않고 BODY에 담아서 보냄 3번의 .setRequestHeader() 과정 필요 */
			/* POST는 데이터를 send()에 담아줌. 키 밸류 순으로 여러 개는 &로 분류 */
			xhr.send('name=홍길동&age=24');
		
		}
		
		
	</script>
	


<!-- 230208 7교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf -->
	<h2>2. jQuery를 이용한 AJAX 테스트</h2>
	
	<!-- jQuery 사용하려면 제이쿼리라이브러리 넣어주기 11행. 개발자 도구 소스탭이나 네트워크탭에 제이쿼리 뜨나 확인 콘솔창에 $ 쳐서 함수 뜨나 보기 -->
	
	<h3>1) GET 방식으로 서버에 데이터 전송 및 응답</h3>
	
	<!-- input에 입력하고 버튼 누르면 서버로 전송했다가 output에 출력되도록 만들기 -->
	<label for="input">입력 : </label>
	<input type="text" id="input" size="30">

	<br>
	<label for="ouput">출력 : </label>
	<input type="text" id="output" size="30" readonly>
	
	<br><br>
	<button id="btn1">GET 방식 전송</button>	
	
	
<!-- 230208 8교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf 198행 BUTTON태그까지-->	
	<h3>2) POST 방식으로 서버에 데이터 전송 및 응답</h3>
	
	<label for="name">이름 : </label>
	<input type="text" id="name"> <br>

	<label for="age">나이 : </label>
	<input type="text" id="age"> <br>

	<label for="output2">출력 : </label>
	<input type="text" id="output2" size="30" readonly> <br><br>
	
	<button id="btn2">POST 방식 전송</button>
	


<!-- 230209 2교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON -->
	<h3>3) 서버에 데이터 전송 후 응답을 객체(Object)로 받기</h3>
	
	<!-- 회원 번호 조회해서 id가 p3인 요소에 (user class에 있는)회원 정보 출력 -->
	<label for="userNo">회원 번호 : </label>
	<input type="text" id="userNo">
	
	<button id="btn3">조회</button>
	
	<p id="p3"></p>
	
	

<!-- 230209 4교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON -->
	<h3>3) 서버에 데이터 전송 후 응답을 리스트(List)로 받기</h3>
	<label><input type="radio" name="gender" value="남자" checked>남자</label>
	<label><input type="radio" name="gender" value="여자">여자</label>
	
	<button id="btn4">조회</button>
	
	<p id="p4"></p>
	
	
	
	<script>
		/* 제이쿼리에서 전체 다 읽고서 읽을 수 있게 해주는 메소드 ready() */
		$(document).ready(function() {
			
			// GET 방식
			$('#btn1').on('click', function() {		// 아이디가 btn1인 요소 가져와서 클릭하면 함수 실행
				
				// 아이디가 input인 요소가 사용자로부터 입력받은 값을 읽어와서 input 변수(let)에 담기
				let input = $('#input').val();
				
				// jQuery에서 AJAX에 값을 보내기
				$.ajax({			// 제이쿼리에서 {}는 객체 생성. 필요한 값들만 객체로 생성
					// type : 전송 방식(GET, POST) 지정
					type: 'GET',
					// url : 요청 URL
					url: '${ pageContext.request.contextPath }/jqAjax1.do',		// 요청을 받아줄 서블릿,html 등의 경로 지정
					// data : 요청 시 전달할 파라미터 설정
					data: {			// data라는 속성에 내가 전송할 데이터를 객체{} 형태로 넘겨줌
						input 		// 'input' : input	객체의 속성명과 값에 해당하는 변수명(208행)이 동일하면 하나만 써도 됨
					},
					// success : ajax 통신이 성공했을 때 실행될 콜백 함수
					success: function(data){	// ajax 통신이 성공했을 때 실행될 콜백 함수
						console.log(data);
					
// 230208 8교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf 			$('#output').val(data);	
					
						$('#output').val(data);				// 아이디가 output인 요소에 받아온 data 출력해줌
						
					},
					// error : ajax 통신이 실패했을 때 실행될 콜백 함수
					error: function(error){		// ajax 통신이 실패했을 때 실행될 콜백 함수
						console.log(error);
					},
					// complete : ajax 통신 성공 실패 여부와 상관 없이 언제나 실행될 콜백 함수
					complete: function() {		// ajax 통신 성공 실패 여부와 상관 없이 언제나 실행될 콜백 함수
						console.log("complete 콜백 함수 실행")
					}
				});
				// url 받아줄 서블릿 생성하기 jqueryAjaxServlet.java
				
			});
			

// 230208 8교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf
			// POST 방식
			$('#btn2').on('click', function() {
				
				//사용자가 입력한 이름, 나이 제이쿼리로 읽어오기
				let name = $('#name').val();	// 아이디가 name인 요소에서 값을 받아와 name 변수에 넣기
				let age = $('#age').val();
				
				// ajax 요청 ajax에 필요한 요소들 객체( {} )로 생성
				$.ajax({
					type: 'POST',
					url: '${ pageContext.request.contextPath }/jqAjax1.do',
					data: {
						'name': name,	// 객체의 속성명과 값에 해당하는 변수명(248행)이 동일하면 하나만 써도 됨
						age				// = 'age': age
					},
					success: (data) => {
						console.log(data);
						
						$('#output2').val(data);		// 아이디가 output2인 요소에 받아온 data 출력해줌
					},
					error: (error) => {
						console.log(error);
					}
				});
			//  ㄴ 제이쿼리로 POST 방식 서버데이터 전송 및 응답은 객체 형태로 요소 정보만 보내면 됨 헤더 설정 필요 없음 알아서 헤더 설정 해줌.
			//     자바스크립트와의 차이점
			});
			
			
			
/* 230209 2교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON */
			/* 제이쿼리는 달러($)로 접근 */
			$('#btn3').on('click', function(){
				/* 회원 번호를 입력하고 버튼을 클릭하면 입력한 번호 가져오게 만들기 */
				let userNo = $('#userNo').val();
				/* alert(userNo); */ /* 이 코드를 통해 입력한 정보(번호)가 가져와지는지 확인 syso(실행 테스트)와 같은 기능 */
				
				$.ajax({
					type: 'GET',
					url: '${ pageContext.request.contextPath}/jsonAjax.do',
					dataType: 'json',	// 응답 데이터 형식 json으로 
					data: {
						userNo 		// 'userNo : userNo' (키값 userNo : 밸류값은 userNo에서 가져다 쓰겠다)는 뜻
					},
					success: (obj) => {
						let result = '';
						
						console.log(obj);
						
						if(obj !== null) {	// 조회된 애가 없으면 null이 나옴 ex) 7~ 이 때 else 구문 출력
							result = '회원 번호 : ' + obj.no +
									 '<br>이름 : ' + obj.name +
									 '<br>나이 : ' + obj.age +
									 '<br>성별 : ' + obj.gender;
						} else {
							result = '사용자 정보가 없습니다.';
						}
						
						$('#p3').html(result);		// 아이디가 p3인 요소에 값 출력
					},
					error: (error) => {
						console.log(error);
					}
				});
			});
		

/* 230209 4교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON */
			$('#btn4').on('click', function() {

				// 제이쿼리에서 input태그의 name속성이 gender인 체크 속성 불러오기
				let gender = $('input[name=gender]:checked').val();
				
				//alert(gender);		// 체크 속성불러온거 확인 like)syso("실테")
				
				$.ajax({
					type: 'POST',
					url: '${ pageContext.request.contextPath}/jsonAjax.do',
					dataType: 'json',
					data: {
						gender // 'gender' : gender  gender라는 키값에 gender 변수를 담아줌
					},
					success: (list) => {
						let result = "";
						
						console.log(list)
						
						// 자바스크립트 each() 반복 메소드
						// list의 i번째에 접근해 반복 조회	
						$.each(list, (i) =>{	// function(인덱스, 현재 요소)
							result += '회원 번호 : ' + list[i].no +
									  '<br>이름 : ' + list[i].name +
									  '<br>나이 : ' + list[i].age +
									  '<br>성별 : ' + list[i].gender +
									  '<br><br>';
							
						});
						$('#p4').html(result);
						
						
					},
					error: (error) => {
						console.log(error);
					}
				});
				
			});
			
			
		})
	
	</script>
	

	
</body>
</html>