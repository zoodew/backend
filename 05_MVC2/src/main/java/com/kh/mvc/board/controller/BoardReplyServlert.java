package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.member.model.vo.Member;

// 230216 6교시 댓글 작성해 등록하기

@WebServlet(name = "boardReply", urlPatterns = { "/board/reply" })
public class BoardReplyServlert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardReplyServlert() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 체크 (직접 적용시켜 보세요.)
		
		int result = 0;
		
		// BoardUpdateServlet에서 로그인멤버얻어오는 코드 가져오기
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
				
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
		// System.out.println(boardNo + ", " + content); // 잘 넘어가는지 확인
		
		Reply reply = new Reply();
		
		reply.setBoardNo(boardNo);
		reply.setWriterNo(loginMember.getNo());
		reply.setContent(content);
		
		result = new BoardService().saveReply(reply);
		
		if(result > 0 ) {
			request.setAttribute("msg", "댓글 등록 성공");
			request.setAttribute("location", "/board/view?no=" + boardNo);
		} else {
			request.setAttribute("msg", "댓글 등록 실패");
			request.setAttribute("location", "/board/view?no=" + boardNo);
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
