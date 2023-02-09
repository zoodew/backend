package com.kh.mvc.member.model.service;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

/*230209 6교시 MVC 로그인 기능 구현*/
public class MemberService {

	public Member login(String userId, String userPwd) {
		
		Member member = null;
		
		member = new MemberDao().findMemberById(userId);
		
		return member;
		
	}

}
