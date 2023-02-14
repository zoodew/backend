package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;


@WebServlet(name = "boardUpdate", urlPatterns = { "/board/update" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
// 230214 6교시 게시글 내 수정 버튼 누르면 수정 페이지로 이동
    public BoardUpdateServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  
// 230214 7교시 게시글 수정 페이지
    	// 로그인 체크, 게시글을 작성한 회원이 맞는지 체크
    	
    	int no = Integer.parseInt(request.getParameter("no"));	// 파라미터에서 no값 가져와 integer로 읽어옴
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if (loginMember != null) {	// 로그인 상태
			
			Board board = new BoardService().getBoardByNo(no);		//  게시글을 작성한 회원이 맞는지 체크 안쪽 if else 까지
						
			if(board != null && loginMember.getId().equals(board.getWriterId())) {
				request.setAttribute("board", board);
				request.getRequestDispatcher("/views/board/update.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/board/list");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			
		} else {	// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
    	
    	
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
// 230214 7교시 게시글 수정을 로그인 한 당사자만 할 수 있게 만들기
    			// 파라미터 변조를 임의로 해도 잘못된 접근으로 인식할 수 있게 하기 
    	
    	// 로그인 체크
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if(loginMember != null) {
			
			// MultipartRequest
	    	
	    	// 파일이 저장될 경로 얻어오기
	    	String path = getServletContext().getRealPath("/resources/upload/board");	// / = 현재 웹 애플리케이션에서 webapp에 해당
	    	
	    	// 파일의 최대 사이즈 지정(10MB로 지정)
	    		// 사이즈 지정은 바이트 단위로 한다. 10485760byte
	    	int maxSize = 10485760;
	    	
	    	// 파일 인코딩 설정
	    	String encoding = "UTF-8";
	    	
	    	// MultipartRequest로 파라미터값 받기
//	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
	    // 위 의 코드를 /mvc/common/util FileRename 클래스 사용을 하면서 아래처럼 바꿈 FileRename 정책
	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
	    		// MultipartRequest(리퀘스트객체, 파일저장경로, 파일최대사이즈, 파일 인코딩 설정, 파일 리네임 정책)
	        	// DefaultFileRenamePolicy() : 중복되는 이름 뒤에 1 ~ 9999 붙인다.
	    	
	    	if(loginMember.getId().equals(mr.getParameter("writer"))) {
	    		
// 230214 8교시 수정한 내용으로 게시글이 바뀌게 만들기
	    		Board board = new Board();
	    		
	    		board.setNo(Integer.parseInt(mr.getParameter("no")));
	    		board.setTitle(mr.getParameter("title"));
	    		board.setContent(mr.getParameter("content"));
	    		
	    		int result = new BoardService().save(board);
	    		
	    		if(result > 0 ) {
	    			request.setAttribute("msg", "게시글 수정 성공");
	    			request.setAttribute("location", "/board/view?no=" + board.getNo());
	    		} else {
	    			request.setAttribute("msg", "게시글 수정 실패");
	    			request.setAttribute("location", "/board/update?no=" + board.getNo());
	    		}
	    		

	    		/*
	    		 * System.out.println(Integer.parseInt(mr.getParameter("no")));
	    		 * System.out.println(mr.getParameter("title"));
	    		 * System.out.println(mr.getParameter("writer"));
	    		 * System.out.println(mr.getParameter("content")); // 수정된 내용이 콘솔에 출력
	    		 */
	    		
	    	} else {
	    		request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/board/list");
				// request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	    	}
	    	
	    	
	    	
	    	
		} else {	// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
	}

}
