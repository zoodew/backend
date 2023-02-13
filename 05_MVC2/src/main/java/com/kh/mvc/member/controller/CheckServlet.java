package com.kh.mvc.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mvc.member.model.service.MemberService;

@WebServlet("/member/idCheck")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CheckServlet() {
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// 230213 1교시 취미 체크박스 선택 안 하고 가입시 null point exception 뜨는 것 해결
		
		Map<String, Boolean> map = new HashMap<>();
		
		String userId = request.getParameter("userId");
		
		
// 230213 2교시 취미 체크박스 선택 안 하고 가입시 null point exception 뜨는 것 해결
		
										// isDuplicateId 메소드 생성 mvc/member/model/service MemberService.java에
										// isDuplicateId() 실제 DB에 값이 있는지를 확인해서 true false 리턴
		map.put("duplicate", new MemberService().isDuplicateId(userId));
		
//		System.out.println(new Gson().toJson(map));
		// 아이디가 중복되면 {"dupicate":true} 중복되지 않으면 {"dupicate":false}  확인 코드라 주석
		
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());
		
		
	}

}
