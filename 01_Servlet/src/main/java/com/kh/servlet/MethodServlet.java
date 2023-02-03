package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 230202 6교시


@WebServlet("/method.do")
public class MethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MethodServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 사용자가 보낸 데이터들은 request 객체 안에 키(input 태그의 name 속성의 값), 값(input 태그의 value 속성의 값) 형태로 담겨있다.
    // request.getParameter("name 속성의 값") : 해당 name 속성의 값을 가지는 요소의 value 값을 문자열로 읽어온다.
    // 체크박스와 같이 하나의 name 속성에 여러 값이 존재하는 경우 .getParameterValues("name 속성의 값") 메소드를 사용한다.
    // .getParameter("name 속성의 값") 메소드를 사용하면 여러 값이 존재하더라도 제일 첫 번째에 있는 값만 읽어온다.
    	String userName = request.getParameter("userName");	// getParameter() : 사용자가 보내주는 데이터를 가지고 오는 메소드
    	String userAge = request.getParameter("userAge");
    	String gender = request.getParameter("gender");
    	String food = request.getParameter("food");
    						// .getParameter는 food 여러 개를 택해도 제일 첫 번째에 있는 값만 가져옴
    	// 여러 값을 가지고 오고 싶다면 .getParameterValues()로 배열 생성
    	String[] foods = request.getParameterValues("food");
    	
    	System.out.println(userName);
    	System.out.println(userAge);
    	System.out.println(gender);
    	System.out.println(food);
    	// 배열 출력은 syso가 아니라 Arrays.stream().forEach() 람다식으로 출력
    	Arrays.stream(foods).forEach(System.out::println);
    	
    	
//230202 7교시
    	
    // 응답 화면 작성
    	
    	// 한글 출력될 수 있도록 설정
    	response.setContentType("text/html;charset=utf-8");
    							// html 타입에 캐릭셋은 utf-8이다
    	
    	// 문자 기반의 출력 스트림
    	PrintWriter out = response.getWriter();
    	
    	out.println("<html>");
    	out.println("<head>");
    	out.println("<title>개인 정보 출력</title>");
    	out.println("</head>");
    	out.println("<body>");
    	out.println("<h2>개인 정보 출력</h2>");    	
    	out.printf("%s님은 %s세 이고 성별은 %s입니다.<br>", userName, userAge, gender);    	
    	out.print("좋아하는 음식은 ");
    	Arrays.stream(foods).forEach(food1 -> out.print(food1 + " "));	// forEach로 반복
    	out.print("입니다.");
    	out.println("</body>");    	
    	out.println("</html>");
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// POST 방식으로 넘겨받는 데이터가 영어 또는 숫자가 아닌 경우에 인코딩 처리를 해야한다.		cf) GET방식은 기본적으로 인코딩 처리가 되어서 나옴
    	// request에서 파라미터 값을 가져오기 전에 UTF-8로 인코딩 설정을 한다. (설정 안 하면 영문, 숫자만 나옴)
    	request.setCharacterEncoding("UTF-8");
		
    	// 입력 받고 출력하고 전송할 값들이 doGet과 동일하다면 굳이 다시 작성할 필요가 없이 아래처럼만 적어두면 됨.	
    	doGet(request, response);
	}

}
