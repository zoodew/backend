package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/*230209 6교시 MVC 로그인 기능 구현*/

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {

    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println(userId + ", " + userPwd);
		// 콘솔에 userId + ", " + userPwd 출력
		
		Member loginMember = new MemberService().login(userId, userPwd);
		// 서비스 객체를 만들어서 사용자로부터 받아온 아이디 패스워드로 login 메소드 실행시키는 Member 타입의 loginMember 객체 생성
		// Member에 커서 두고 ctrl 1 로 클래스 만들기, MemberService 클래스 같은 방법으로 만들기, MemberService에 login 메소드 생성
		
		System.out.println(loginMember);
		
		response.sendRedirect(request.getContextPath() + "/");
		// 다시 홈으로 돌아오게 redirect 시킴.
		
	}

}
