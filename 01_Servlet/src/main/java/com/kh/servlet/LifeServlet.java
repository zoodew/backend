package com.kh.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/life.do")
public class LifeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 서블릿은 최초로 요청이 있을 때만 생성 새로고침을 두 번 하면 두 번째부터는 나오지 않음
    public LifeServlet() {
    	System.out.println("LifeServlet 생성");
    }

    // 툴로 서블릿 작성하면 override 어노테이션이 들어가지 않음 생성자( public LifeServlet() { ) 빼고 다 넣어주기
    @Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
	}

    @Override
	public void destroy() {
		System.out.println("destroy() 호출");
	}

    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 호출");
		
		// 요청 방식이 GET인지, POST인지 확인 후 doGet() 또는 doPost() 메소드를 실행
		super.service(request, response);
	}

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");

		doGet(request, response);
	}

}
