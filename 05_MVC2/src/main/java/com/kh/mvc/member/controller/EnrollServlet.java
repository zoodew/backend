package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

// 230210 4교시 회원가입 기능 구현


@WebServlet(name = "enroll", urlPatterns = { "/member/enroll" })
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnrollServlet() {
    }


    // 겟으로는 회원가입 페이지 포워드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 페이지로 포워드 하는 기능	
		request.getRequestDispatcher("/views/member/enroll.jsp").forward(request, response);
	}

	// 포스트로는 받은 회원가입정보를 db에 저장하고 보냄
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 230210 7교시 서블릿 필터 기능 실습 위해 주석
//		request.setCharacterEncoding("UTF-8");
		
		Member member = new Member();
		
		// Member에 해당하는 필드들을 enroll.jsp에서 사용자가 저장하는 값으로 채워서 인스턴스 생성
		member.setId(request.getParameter("userId"));		// userId > enroll.jsp 에서 set받아온 속성값을 넘겨주는 것
		member.setPassword(request.getParameter("userPwd"));
		member.setName(request.getParameter("userName"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setAddress(request.getParameter("address"));
		member.setHobby(String.join(",", request.getParameterValues("hobby")));
						//.join("구분자", 배열) 배열의 문자열을 구분자로 구분해서 하나의 문자열로 생성
						// setHobby()가 String으로 매개값을 받아서 .join()메소드 사용해서 하나의 문자열로 생성해줌
		
		System.out.println(member);
		
		
		// 받아온 회원가입 정보를 db에 저장
		int result = new MemberService().save(member);
										// save() 인서트 하는 작업.
		
		System.out.println(result);
	

// 230210 5교시 회원가입 기능 구현	
		
		if(result > 0) {	// result 시 회원가입이 되면 행 하나가 추가가 돼서 1이상이 나옴
			//회원 가입 완료 시
			request.setAttribute("msg", "회원 가입 성공");			// 회원 가입 성공 얼럿창 띄움
			request.setAttribute("location", "/");				// 홈으로 이동
		} else {
			request.setAttribute("msg", "회원 가입 실패");			// 회원 가입 실패 얼럿창 띄움
			request.setAttribute("location", "/member/enroll");	// /enroll로 이동
			
			/*
			 * enroll 잘 나오나 확인하기
			 * 
			 * /member/model/service MemberServie.java 의 public int save 의 result = new MemberDao().insertMember(member);를 주석으로 묶기
			 * 묶으면 result값으로 무조건 0이 나옴
			 * 이렇게 해놓고 회원가입 해보면 회원가입실패가 뜨고 enroll로 이동하는게 정답
			 * 이렇게 되는지 확인하고
			 * DB 삭제하고 result = new MemberDao().insertMember(member); 주석도 다시 풀어놓기
			 * 
			 */		
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
