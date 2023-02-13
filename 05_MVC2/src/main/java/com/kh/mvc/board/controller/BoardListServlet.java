package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	int page = 0;
    	PageInfo pageInfo = null;
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
			// null이 아니라 잘못된 타입의 값으로 (ex 문자타입,,,,) 나올 때를 위해 예외처리로 해결
		} catch (NumberFormatException e) {
			page = 1;	// NumberFormatException이 나오면 page = 1로
		}
    	
    	pageInfo = new PageInfo(page, 10, 222, 10);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.getRequestDispatcher("/views/board/list.jsp").forward(request, response);
    	
    	

    }

    
}
