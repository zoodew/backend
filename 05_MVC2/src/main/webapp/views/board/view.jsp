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
<!-- 230214 6교시 게시글 내에서 첨부파일 이름 보이게 만들기 -->
				<th>첨부파일</th>
				<td>
					<c:if test="${ empty board.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ not empty board.originalFileName }">
						<span> ${ board.originalFileName }</span>
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
						<button type="button">삭제</button>
					</c:if>
<!-- 230214 4교시 상세 게시물에서 목록으로 가게 만들기 -->
					<button type="button" onclick="location.href='javascript:history.back()'">목록으로</button>
										<!-- onclick="location.href='${ path }/board/list'" 상세 게시물에서 1페이지 목록으로 보내는 코드 -->
										<!-- onclick="location.href='javascript:history.back()'" 상세 게시물에서 현재 게시물이 있는 목록으로 보내는 코드 javascript: 생략 가능 -->		
				</th>
			</tr>
		</table>
		<div id="comment-container">
	    	<div class="comment-editor">
	    		<form action="${ path }/board/reply" method="POST">
	    			<input type="hidden" name="boardNo" value="">
	    			<input type="hidden" name="writer" value="">
					<textarea name="content" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>	    			
	    		</form>
	    	</div>
	    </div>
	    <table id="tbl-comment">
    	   	<tr class="level1">
	    		<td>
	    			<sub class="comment-writer">aa</sub>
	    			<sub class="comment-date">2021.05.07</sub>
	    			<br>
	    			컨텐츠
	    		</td>
	    		<td>
    				<button class="btn-delete">삭제</button>

	    		</td>
	    	</tr>
	    </table>
    </div>
</section>

<jsp:include page="/views/common/footer.jsp" /> 