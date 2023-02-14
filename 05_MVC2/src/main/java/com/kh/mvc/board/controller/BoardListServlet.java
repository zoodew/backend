package com.kh.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;


@WebServlet(name = "boardList", urlPatterns = { "/board/list" })
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardListServlet() {
    }

// 230213 7.8교시 게시판 구현 게시글 목록
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    // 로그인 체크는 필요 없다. 게시판은 로그인 안 해도 들어갈 수 있게 만들어서
    	int page = 0;	// 페이지 번호 받아오는 코드.
// 23021 1교시 게시판 구현 게시글 목록    	
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	
// 230221 2교시 게시글 목록 가져오기 각 게시글을 리스트에 담기
    	List<Board> list = null;
    	
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
			// null이 아니라 잘못된 타입의 값으로 (ex 문자타입,,,,) 나올 때를 위해 예외처리로 해결
		} catch (NumberFormatException e) {
			page = 1;	// NumberFormatException이 나오면 page = 1로
		}
    	
    	listCount = new BoardService().getBoardCount();
    	pageInfo = new PageInfo(page, 10, listCount, 10);
    												// ㄴ 사용자가 원하는대로 나오게 하려면 파라미터값으로 변경. 10개씩 보기 20개씩 보기...
    	list = new BoardService().getBoardList(pageInfo);
    	
    	System.out.println(list);
    		// 콘솔창에 list 뜨는지 확인 BoardServiceDao의 List<Board> 형태로
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("list", list);
    	request.getRequestDispatcher("/views/board/list.jsp").forward(request, response);
    	
    	

    }

    
}
