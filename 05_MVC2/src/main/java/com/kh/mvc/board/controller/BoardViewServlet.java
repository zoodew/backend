package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;

@WebServlet(name = "boardView", urlPatterns = { "/board/view" })
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardViewServlet() {
    }

// 230214 3교시 게시판 제목 클릭시 나오는 상세 페이지 기능 만들기
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Board board = null;
		
		// 사용자가 보내준 게시글 번호 받기
			// 정수값으로 받아야 해서 Integer.parseInt()
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("게시글 번호 : " + no);

		
// 230216 7.8교시 한 번 조회한 회원은 조회수 처리에 포함되지 않게 만들기
		// 내가 봤던 게시글의 번호가 들어가는 쿠키 생성. 쿠키가 존재하면 조회수 늘어나지 않게 만들기
		
		// 지금 이 상태 세션 기준! 세션 = 하나의 브라우저와 관련된 영역 session 내장 객체를 통해 접근할 수 있는 영역.
		// 때문에 로그아웃 상태, 계정1, 계정2를 다 각 개인으로 보지 않고 하나로 봐서 한 번 조회수가 올라가면 다른 계정으로 로그인해도 조회수가 증가되지 않음
		
		// 1. 쿠키에 게시글을 조회한 이력이 있는지 확인
			// 쿠키를 배열로 만들기
		Cookie[] cookies = request.getCookies();
		String boardHistory = "";
		boolean hasRead = false;

		if(cookies != null) {
			// String name = null;								1) if(name.equals("boardHistory"))의 name에 인라인 처리로 생략			
			
			for (Cookie cookie : cookies) {	// for( cookie에 담아주기 : cookies 배열의 개수만큼 반복해서) 
				// name = cookie.getName();	// 쿠키 이름 받아옴		2) if(name.equals("boardHistory"))의 name에 인라인 처리로 생략
				// System.out.println(name);		// 잘 받았나 확인 코드 없어도 됨		인라인 처리하면 불가능
				
				if(cookie.getName().equals("boardHistory")) {			// cookies의 이름(키값)이 boardHistory 이면
					boardHistory = cookie.getValue();					// ㄴ value를 가져와 boardHistory에 넣음
					
					if(boardHistory.contains("|" + no + "|")) {
						hasRead = true;						// 이미 본 상태면 true 뜸
						
						break;	// 반복문 끝나게(for문 빠져나가게 )break 걸어줌
					}
				}
			}
		}
		
		// 2. 읽지 않은 게시글이면(조회한 이력이 없으면) 쿠키에 기록
		if(!hasRead) {
			Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + no + "|");
			// 개발자도구의 애플리케이션의 쿠키 선택 후 쿠키 이름, 값 조회 가능
			
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		}
			// 실제로 조회수 증가 시키는 메소드 BoardService().getBoardByNo()
			// getBoardByNo에 위의 2번을 넘겨줌.	아래 식도 수정 BoardService의 getBoardByNo()
		board = new BoardService().getBoardByNo(no, hasRead);
		
// 230216 5교시 댓글 조회
		
		
		System.out.println(board);
		
		request.setAttribute("board", board);
		request.getRequestDispatcher("/views/board/view.jsp").forward(request, response);
	}

}
