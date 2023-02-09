package com.kh.ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*230208 5교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf*/
@WebServlet("/jsAjax.do")
public class JavascriptAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public JavascriptAjaxServlet() {
    }

// 230208 5교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf
    @Override	// GET 방식은 여기서
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    				

    	String name = request.getParameter("name"); 	// 겟파라미터를 통해 네임값 가져옴
    	String age = request.getParameter("age");		// 겟파라미터를 통해 에이지값 가져옴
		
    	System.out.println(name + ", " + age);
    	
    	// 서버 실행시켜서 get방식 전송 버튼 누르면 console 창에 이름(문인수) 나이(19) 찍힘 index.jsp의 3. open() 메소드 호출에서 담은 값
    	// 서버 호출시 url에 값 담아서 보냄.
    	
    	response.setContentType("text/html;charset=UTF-8"); // 출력스트림 얻어서
    	
		response.getWriter().append("서버에서 전송할 값<br>").append(name + ", " + age);
	}						// append 데이터 보냄


    
// 230208 6교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf
    @Override	// POST 방식은 여기서
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* request.setCharacterEncoding("UTF-8"); */			// POST는 파라미터로 받아오기 전에 캐릭터 인코딩 해주기
    		// ㄴ index.jsp의 151행 같이 보기
    	String name = request.getParameter("name"); 	// 겟파라미터를 통해 네임값 가져옴
    	String age = request.getParameter("age");
    	
    	System.out.println(name + ", " + age);
    	
    	response.setContentType("text/html;charset=UTF-8"); // 출력스트림 얻어서
    	
		response.getWriter().append("서버에서 전송할 값<br>").append(name + ", " + age);
						// append 데이터 보냄
	}

}
