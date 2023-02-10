package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.common.util.EncryptUtil;
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
    	HttpSession session = null;
    	
    	String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
// 230210 아이디 자동저장 기능
		String saveId = request.getParameter("saveId");
		
		// 콘솔에 userId + ", " + userPwd 출력
		System.out.println(userId + ", " + userPwd + ", " + saveId);
			// saveId > 아이디저장 체크박스를 체크하면 on으로 체크하지 않으면 null로 뜬다.
	
// 230210 8교시 패스워드 암호화하여 DB에 저장하는 기능
		System.out.println(EncryptUtil.oneWayEnc(userPwd, "SHA-256"));
		//A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ= 로 출력
		
		if(saveId != null) {	// null이 아닐 때 = 아이디 저장 체크박스 체크 했을 때
			// 현재 전달된 아이디를 쿠키에 저장
			
			// 1. 쿠키 생성
								// saveId로 사용자가 전달해준 아이디 전달
			Cookie cookie = new Cookie("saveId", userId);
			
			// 2. response 객체에 쿠키 추가
					// cookie.setMaxAge(-1); // 브라우저가 종료될 때까지 유지되는 것 : 세션쿠키
			cookie.setMaxAge(259200);		// 3일 동안 접속 안 해도 접속 유지되게 만들기 (초 단위) 3일 = 259200초
			response.addCookie(cookie);
				// 개발자도구 애플리케이션 탭 쿠키에서 쿠키 잘 만들어졌는지 확인 가능

		} else {
			// 체크를 안 하면 더 이상 아이디를 저장하지 않겠다는 뜻
			
			// 기존 쿠키 값 삭제
				// 동일한 쿠키 다시 생성 후 setMaxAge 값을 0으로 설정
			Cookie cookie = new Cookie("saveId", "");
			
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
		}
		
		Member loginMember = new MemberService().login(userId, userPwd);
		// 서비스 객체를 만들어서 사용자로부터 받아온 아이디 패스워드로 login 메소드 실행시키는 Member 타입의 loginMember 객체 생성
		// Member에 커서 두고 ctrl 1 로 클래스 만들기, MemberService 클래스 같은 방법으로 만들기, MemberService에 login 메소드 생성
		
		System.out.println(loginMember);
		
		
/*230210 2교시 MVC 로그인 기능 구현*/	
		
		if(loginMember != null) {
			// loginMember가 널이 아니면 > 로그인 성공
			
			// loginMember 세션에 저장
				//	session 서버가 클라이언트에 대한 정보를 가지고 있는 영역. 하나의 브라우저에 접속하면 그 브라우저가 닫힐 때까지 유효
			session = request.getSession();
			
			session.setAttribute("loginMember", loginMember);
			
			
			// 로그인이 완료되면 폼 화면으로 이동시킨다.
			response.sendRedirect(request.getContextPath() + "/");			
		} else {	// loginMember가 널이면 > 로그인 실패
			
			// 로그인 실패에 대한 메시지를 띄워주고 홈 화면으로 이동
			
			// 1. 공용으로 사용하는 에러 메시지 출력 페이지에 전달할 메시지와 메시지 출력 후 이동할 페이지를 request 객체에 저장한다.
			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
							// webapp/views/common/msg.jsp 보기
			request.setAttribute("location", "/");
											// 홈으로 이동이니 슬래시만 줌
			
			// 2. request 객체의 데이터를 유지해서 에러 메시지를 출력 페이지에 전달하기 위해 forward를 실행한다.
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		/*
		 * response.sendRedirect(request.getContextPath() + "/");
		 * // 다시 홈으로 돌아오게 redirect 시킴. 얘를 if 문 만들면서 위로 올려버림 // 로그인이 완료되면 폼 화면으로 이동시킨다.로
		 */		
	}

}
