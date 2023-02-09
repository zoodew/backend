package com.kh.ajax.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.User;

/*230209 2교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON*/

@WebServlet("/jsonAjax.do")
public class JsonAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public JsonAjaxServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*230209 3교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON*/
		// 사용자 정보가 저장되어 있는 List 객체 생성
		List<User> list = new ArrayList<>();
		
		list.add(new User(1, "문인수", 20, "남자"));
		list.add(new User(2, "김철수", 16, "남자"));
		list.add(new User(3, "김영희", 16, "여자"));
		list.add(new User(4, "홍길동", 20, "남자"));
		list.add(new User(5, "영심이", 15, "여자"));
		list.add(new User(6, "왕경태", 15, "남자"));
		
							// parseInt() 숫자형태의 문자열을 정수값으로 변환하여 리턴하는 메소드.
		int userNo = Integer.parseInt(request.getParameter("userNo"));

		User findUser = list.stream()
							.filter(user -> user.getNo() == userNo)
							.findFirst()
							.orElse(null);
		
		System.out.println(findUser);
		System.out.println(new Gson().toJson(findUser));
									// toJason() : 자바객체를 문자열로
		System.out.println(new Gson().fromJson("{\"no\":5,\"name\":\"영심이\",\"age\":15,\"gender\":\"여자\"}", User.class));
									// fromJson() : 문자열을 자바 객체로

/*230209 2교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON*/

		/* JSON으로 응답을 할 것. 응답하는 데이터는 제이슨. 응답하기 전에 데이터타입 미디어타입으로 지정. 캐릭셋으로 인코딩*/
		response.setContentType("application/json;charset=UTF-8");
//				mime type 작성법 application/json 포맷의 charset=UTF-8야
//		제이쿼리로 작성된 값을 서버에서 내려준 데이터 타입이 제이슨 타입이면. 전달해주는 데이터를 자바스크립트 객체로 변환해서 success의 매개변수로 넘겨줌
		
//		response.getWriter().write("{\"no\": 1, \"name\": \"문인수\"}");
							// 자바스크립트의 객체 표현법을 사용하고 있으나 자바스크립트 객체가 아니라 JSON 문자열 포맷
//		response.getWriter().printf("{\"no\": %d, \"name\": \"%s\"}", findUser.getNo(), findUser.getName());
			//list 값 출력해줌
		
//		new Gson().toJson(findUser, response.getWriter());
		/*
		 * toJson		: 제이슨 만들어주는 메소드 자바 객체를 문자열로!
		 * 				  findUser 객체를 넘겨서 제이슨으로 만들고 response.getWriter()에 넘겨줌
		 * 			
		 * fromJson()	: 문자열을 자바 객체로
		 */
		response.getWriter().write(new Gson().toJson(findUser));
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
/*230209 4교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON*/	
		
		// 사용자 정보가 저장되어 있는 List 객체 생성
		List<User> list = new ArrayList<>();
				
		list.add(new User(1, "문인수", 20, "남자"));
		list.add(new User(2, "김철수", 16, "남자"));
		list.add(new User(3, "김영희", 16, "여자"));
		list.add(new User(4, "홍길동", 20, "남자"));
		list.add(new User(5, "영심이", 15, "여자"));
		list.add(new User(6, "왕경태", 15, "남자"));
		
		String gender = request.getParameter("gender");
		
//		System.out.println(gender);							// 이클립스 서버 콘솔에 출력
		
		List<User> findUsers = list.stream()
									.filter(user -> user.getGender().equals(gender))
									.collect(Collectors.toList());
		
		System.out.println(findUsers);						// 위의 list를 통해 성별이 남자이면 네 개의 객체 조회, 여자면 두 개의 객체 조회
		System.out.println(new Gson().toJson(findUsers));
		// 출력됨 [{"no":1,"name":"문인수","age":20,"gender":"남자"},{"no":2,"name":"김철수","age":16,"gender":"남자"},{"no":4,"name":"홍길동","age":20,"gender":"남자"},{"no":6,"name":"왕경태","age":15,"gender":"남자"}]
		//	제이슨에서는 리스트를 조회할 때 문자열을 자바스크립트 배열 만드는 형태로 만들어줌. 리스트 같은 경우는 자바스크립트 배열(대괄호[] : 배열)처럼 표기해줌.
	
		
		
/*230209 5교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON*/	
		
		/* JSON으로 응답을 할 것. 응답하는 데이터는 제이슨. 응답하기 전에 데이터타입 미디어타입으로 지정. 캐릭셋으로 인코딩*/
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(findUsers, response.getWriter());
		// 서버가 전달해준 제이슨 데이터를 자바스크립트의 형태로 조회해줌. 자바스크립트의 배열 형태
		
		
}

	
}
