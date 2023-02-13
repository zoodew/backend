package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "myPage", urlPatterns = { "/member/myPage" })
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/* 230213 2교시 내 정보 버튼 클릭하면 사용자 정보 보이게 */
		
		// 로그인 된 사용자인지 체크
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
							// 세션이 null이면 null 넣고 null이 아니면 session.getAttribute("loginMember");
		
		// 로그인이 된 사용자만 myPage 페이지로 넘어가게 만들기

		if(loginMember != null) {
			request.getRequestDispatcher("/views/member/myPage.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");

			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		/*
		 * 
		 * java.lang.IllegalStateException 에러발생 Controller의 @RequestMapping 의 값이 중복되어 나타나는 에러
		 * 
		 * 위의 else 구문 중복
		 * 
		 * // views/member/myPage로 forward
		 * request.getRequestDispatcher("/views/member/myPage.jsp").forward(request,
		 * response);
		 */
		
		
	}

}
