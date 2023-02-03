package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 230202 4교시 툴을 사용한 서블릿 생성


// @WebServlet : 서블릿을 등록, URL 매핑

// r 서블릿 이름은 기본적으로 클래스 이름과 동일함. 변경을 원할 시 (name = "이름 지정", urlPatterns = "url 패턴 지정")
@WebServlet(name = "second", urlPatterns = "/second.do")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SecondServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());
		System.out.println(request.getServerName());
		System.out.println(request.getServerPort());
		System.out.println(request.getRemoteAddr());
		
		
	//브라우저 창에 띄우는 방법
		// 작성한 응답페이지를 브라우저에게 전달하는 역할을 하는 메소드
		// getWriter();			문자기반의 출력스트림
		// getOutputStream();
		
		// .setContentType()
		// 한글이 깨지는 것을 방지하기 위해서 Content-Type 응답 헤더를 설정하는 메소드이다.
		// HTML이 UTF-8 형식이라는 것을 브라우저에게 알린다.
		response.setContentType("text/html;charset=utf-8");
		
		// .getWriter()	응답 화면을 출력하기 위한 출력 스트림을 얻어온다.
		PrintWriter out = response.getWriter();
		
		// 자바 코드로 응답 화면을 작성
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>hi.</h1>");	// 영문은 출력 가능한데 한글은 출력되지 않음
		out.write("<h1>우리가 만든 두 번째 서블릿이 반환한 내용</h1>");	// 40행을 추가해줌 인코딩 설정
		out.write("</body>");
		out.write("</html>");
	}

}
