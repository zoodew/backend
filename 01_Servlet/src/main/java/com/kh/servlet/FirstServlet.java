package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 230202 3교시
/*
 * 서버에서 서블릿을 실행하는 방법
 * 
 * 	1. 서블릿 클래스를 작성한다.
 * 		- javax.servlet.http.HttpServlet 클래스를 상속한다.
 * 		- doGet(), doPost() 메소드를 재정의한다.
 * 	
 * 	2. 서블릿 등록 및 URL을 매핑한다.
 * 		- web.xml에 서블릿을 등록 및 URL을 매핑한다.
 * 		- @webServlet으로 서블릿을 등록 및 URL 매핑한다.
 * 
 */
public class FirstServlet extends HttpServlet/* ctrl space i초록 동그라미에 a 붙은 추상클래스 선택*/{

	private static final long serialVersionUID = 1L;
	
// 230202 3교시
	
	// 사용자의 요청을 처리하는 메소드들 재정의(override)
	//	alt shift s override/implement methods
	
	@Override							// request 객체			// reseponse 객체
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("사용자로부터 GET 요청을 받음");
		
	//브라우저 창에 띄우는 방법
		// 작성한 응답페이지를 브라우저에게 전달하는 역할을 하는 메소드
		// getWriter();			문자기반의 출력스트림
		// getOutputStream();
		
		// .setContentType()
		// 한글이 깨지는 것을 방지하기 위해서 Content-Type 응답 헤더를 설정하는 메소드이다.
		// HTML이 UTF-8 형식이라는 것을 브라우저에게 알린다.
		resp.setContentType("text/html;charset=utf-8");
		
		// .getWriter()	응답 화면을 출력하기 위한 출력 스트림을 얻어온다.
		PrintWriter out = resp.getWriter();
		
		// 자바 코드로 응답 화면을 작성
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>hi.</h1>");	// 영문은 출력 가능한데 한글은 출력되지 않음
		out.write("<h1>우리가 만든 첫 번째 서블릿이 반환한 내용</h1>");	// 40행을 추가해줌 인코딩 설정
		out.write("</body>");
		out.write("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
