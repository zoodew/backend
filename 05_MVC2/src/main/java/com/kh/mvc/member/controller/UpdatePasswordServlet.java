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

@WebServlet(name = "updatePwd", urlPatterns = { "/member/updatePwd" })
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdatePasswordServlet() {
}

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
// 230213 5교시 회원정보 수정 비밀번호 변경하기
    	request.getRequestDispatcher("/views/member/updatePwd.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
// 230213 5교시 회원정보 수정 비밀번호 변경하기
    	int result = 0;
    	
    	// 사용자가 보내는 패스워드 받기 updatePwd.jsp의 변경할 비밀번호 input 태그
    	String userPwd = request.getParameter("userPwd");
    	
    	// 로그인 체크
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if(loginMember != null) {
		
			result = new MemberService().updatePassword(loginMember.getNo(), userPwd);
										// 메소드 생성 으로 에러 제거 MemberService.java
			
			if(result > 0) {	
				session.setAttribute("loginMember", new MemberService().findMemberById(loginMember.getId()));
				
				request.setAttribute("msg", "비밀번호 변경이 완료되었습니다.");
				request.setAttribute("script", "self.close()");
									// 비밀번호 변경이 되면 msg.jsp의 script구문을 실행 self.close가 되어서 msg.jsp의 script 다음이 실행되지 않음
				
			} else {

				request.setAttribute("msg", "비밀번호 변경에 실패하였습니다.");
				request.setAttribute("location", "/member/updatePwd");
			}
			
			
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}

			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
    	//System.out.println(userPwd);	// 입력한 값 잘 넘어오는지 확인하는 syso

    	


}
