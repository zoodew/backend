package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "update", urlPatterns = { "/member/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// 230213 3교시 	회원정보 수정하기
		Member member = null;
		int result = 0;
		
		// 1. 로그인 된 사용자인지 체크
		// 수정 부분 로그인 유무 체크가 되어야 함 session에 시간이 걸려있어서 (15분 후 자동로그아웃) 로그아웃이 되어있는데 수정 버튼 누르면 잘못된 접근이 되기때문에
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if (loginMember != null) {	// 로그인 상태
			
			// 2. 사용자가 수정한 내용을 가지고 Member 객체를 생성
			member = new Member();
			
			member.setNo(loginMember.getNo());					// 식별할 회원 번호를 세션영역에 저장된 loginMember에서 가져옴
			member.setName(request.getParameter("userName"));
			member.setPhone(request.getParameter("phone"));
			member.setEmail(request.getParameter("email"));
			member.setAddress(request.getParameter("address"));
			
			String hobby = request.getParameterValues("hobby") != null ?
					String.join(",", request.getParameterValues("hobby")) : null;
			
			member.setHobby(hobby);
			
			// 3. 회원 정보 수정
			result = new MemberService().save(member);		// save에 member 오브젝트를 넘김
				
			if(result > 0) {
				// 정보 수정 성공
// 230213 4교시 회원 정보 수정
				// 세션을 갱신한다.
				session.setAttribute("loginMember", new MemberService().findMemberById(loginMember.getId()));
				
				request.setAttribute("msg", "회원 정보 수정 완료");
				request.setAttribute("location", "/member/myPage");
			} else {
				// 정보 수정 실패
				request.setAttribute("msg", "회원 정보 수정 실패");
				request.setAttribute("location", "/member/myPage");
			}
			
		} else {	// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

}
