<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />

<style>
    section>div#board-write-container{width:600px; margin:0 auto; text-align:center;}
    section>div#board-write-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    div#comment-container button#btn-insert{width:60px;height:50px; color:white; background-color:#3300FF;position:relative;top:-20px;}
    
    /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
</style>
<section id="content">   
	<div id="board-write-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
<!-- 230214 3교시 상세 게시글 화면에 그려주기 -->
				<td>${ board.no }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>${ board.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ board.writerId }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ board.readCount }</td>
			</tr>
			<tr>
<!-- 230214 6교시 게시글 내에서 첨부파일 이름 보이게 만들기 test 속성-->
				<th>첨부파일</th>
				<td>
					<c:if test="${ empty board.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ not empty board.originalFileName }">
<!-- 230216 3교시 첨부파일 다운로드하기 a 태그 추가 href, id 속성 a태그 클릭시, script 쪽으로 가기-->
		<!-- 230214 4교시 첨부파일 다운로드하기 글 작성할 때 첨부파일 넣으면 서버로 전달 -->
						<!-- webapp이 아닌 별도의 저장폴더를 쓴다면 파일을 스트림으로 읽어와서 그거로 다운받아야 함 -->
						<a href="javascript:" id="fileDown">
							<span> ${ board.originalFileName }</span>
						</a>
						<!-- webapp 밑에 다운로드를 받아서 저장했다면 url로 파일에 접근이 가능해 a태그로도 파일을 저장하고 열 수 있다. -->
						<%--
						<a href="${ path }/resources/upload/board/${ board.renamedFileName}"
                    	 					download="${ board.originalFileName }" >
                  			<span> ${ board.originalFileName } </span>
                  		</a>
						--%>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ board.content }</td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			<tr>
				<th colspan="2">
<!-- 230214 3교시 수정 삭제 로그인 한 작성자만 보이게 하기 -->
					<c:if test="${ not empty loginMember && loginMember.id == board.writerId }">
<!-- 230214 6교시 게시글 내 수정 버튼 누르면 수정 페이지로 이동 -->
						<button type="button" onclick="location.href='${ path }/board/update?no=${ board.no }'">수정</button>
<!-- 230216 2교시 게시글 삭제하기 -->						
						<button type="button" id="btnDelete">삭제</button>
					</c:if>
<!-- 230214 4교시 상세 게시물에서 목록으로 가게 만들기 -->
					<button type="button" onclick="location.href='${ path }/board/list'">목록으로</button>
										<!-- onclick="location.href='${ path }/board/list'" 상세 게시물에서 1페이지 목록으로 보내는 코드 문제! 모든 수정하기에서 뒤로가기가 됨...-->
										<!-- onclick="location.href='javascript:history.back()'" 상세 게시물에서 현재 게시물이 있는 목록으로 보내는 코드 javascript: 생략 가능 -->		
				</th>
			</tr>
		</table>
<!-- 230216 6교시 댓글 작성란 만들기 -->
		<div id="comment-container">
	    	<div class="comment-editor">
	    		<form action="${ path }/board/reply" method="POST">
	    			<input type="hidden" name="boardNo" value="${ board.no }">
	    <!-- 로그인 한 회원만 댓글 작성할 수 있게하기 id속성 주고 javascript script 태그로-->	
					<textarea name="content" id="replyContent" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>	    			
	    		</form>
	    	</div>
	    </div>
<!-- 230216 6교시 댓글 화면에 표시하기 -->
	    <table id="tbl-comment">
	    <!-- tr태그 자체가 하나의 댓글. 여러 댓글이 있을 수 있으니 foreach문으로 만들어주기 c:foreach -->
    	   	<c:forEach var="reply" items="${ board.replies }">    	   	
	    	   	<tr class="level1">
		    		<td>
		    			<sub class="comment-writer">${ reply.writerId }</sub>
		    			<sub class="comment-date">${ reply.createDate }</sub>
		    			<br>
		    			<span>${ reply.content }</span>
		    		</td>
		    		<td>
		 <!-- loginMember이면서 loginMember의 ID와 댓글 작성한 사람의 ID가 같을 때 버튼태그보이게 -->		
		    			<c:if test="${ not empty loginMember && loginMember.id == reply.writerId}">
		    				<button class="btn-delete">삭제</button>
		    			</c:if>
	
		    		</td>
		    	</tr>
    	   	</c:forEach>
	    </table>
    </div>
</section>
<script>	/* 제이쿼리 영억 */
<!-- 230216 2교시 게시글 삭제하기 -->	
	$(document).ready(() => {
		$('#btnDelete').on('click', () => {
			if(confirm('정말로 게시글을 삭제하시겠습니까?')) {
				location.replace('${ path }/board/delete?no=${ board.no }');
							// 요청이 왔을 때 받을 서블릿 생성. BoardDeleteServelt.java
			}
		});
	
<!-- 230216 3교시 첨부파일 다운로드하기 a 태그 누르면 -->
		$('#fileDown').on('click', () => {
						// encodeURIComponent() URI로 데이터를 전달하기 위해서 문자열을 인코딩 첨부파일 이름이 한글이거나 공백 특문인경우 url에 이상하게 나오는데 그거를 바꿔줌
			let oname = encodeURIComponent('${ board.originalFileName }');
	        let rname = encodeURIComponent('${ board.renamedFileName }');
	       
	        location.assign('${ path }/board/fileDown?oname=' + oname + '&rname=' + rname);
									// ㄴ board/fileDown 요청 처리할 수 잇는 서블릿 생성. BoardFileDownServelt.java
									
	        // location.assign('${ path }/board/fileDown?oname=${ board.originalFileName }&rname=${ board.renamedFileName }');
									
		/*
			getParameter()의 매개변수는 어디서 나온 건가요???
				태그의 name 속성!
				but! 무조건 태그의 name 속성이 아니라 받아온 값의 이름을 써준다고 생각하기
		 */
		});

		
<!-- 230216 6교시 로그인 한 회원만 댓글 작성할 수 있게하기 -->
		$('#replyContent').on('focus', () => {
			if(${ empty loginMember }) {
				alert('로그인 후 이용해 주세요.')
				
				$('#userId').focus();
			}			
		});
		
		

	});
</script>



<jsp:include page="/views/common/footer.jsp" /> 