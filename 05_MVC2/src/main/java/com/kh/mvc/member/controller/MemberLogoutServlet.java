package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 230210 3교시 로그아웃 기능 구현 서블릿

@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberLogoutServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// 로그아웃 순서
    	
    	// 1. 세션을 얻어온다.
    	
    	// 현재 세션 객체가 있으면(true) 세션 객체를 가져오고 없으면(false) null을 리턴한다.
    	HttpSession session = request.getSession(false);
    											   	
    	// 2. 세션을 삭제한다.
    	if(session != null) {
    		// 세션을 삭제하는 메소드
    		session.invalidate();
    	}
    	
    	// 3. 삭제 후 홈 화면으로 리다이렉트
    	response.sendRedirect(request.getContextPath() + "/");
    												// 홈으로 이동 슬래쉬/ 슬래쉬가 홈이라는 뜻 아니고??????????? 슬래쉬찾아보기!!!????
	}			

}
