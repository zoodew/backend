package com.kh.ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jqAjax1.do")
public class jqueryAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public jqueryAjaxServlet() {
    }

/* 230208 8교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String input = request.getParameter("input");	// 겟파라미터를 통해 입력값을 얻어옴
		
    	System.out.println("입력 값 : " + input);			// 얻어온 값 콘솔에 출력
    	
    	response.setContentType("text/html;charset=UTF-8");	// 한글 인코딩
    	response.getWriter().printf("입력 값 : %s, 길이 : %d", input, input.length()); // 얻어온 값 콘솔에 출력
    	
    	
	}


/* 230208 8교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 제이쿼리 방식은 한글 인코딩 별도로 설정 안 해줘도 됨 cf)javascriptAjaxServlet.java
    	// 사용자로부터 입력받은 값 서버에 요청할 때 인코딩
    	
    	String name = request.getParameter("name");	// 겟파라미터를 통해 입력값을 얻어옴
    	String age = request.getParameter("age");
    	
    	System.out.println(name + ", " + age);		// 얻어온 값 콘솔에 출력
    	
    	response.setContentType("text/html;charset=UTF-8"); // 서버에서 받아와 응답할 때 인코딩 값
    	response.getWriter().print(name + ", " + age);		// 얻어온 값 콘솔에 출력
    	
    	
    	
    	
	}

}
