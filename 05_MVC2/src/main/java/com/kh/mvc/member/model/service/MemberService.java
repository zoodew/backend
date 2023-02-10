package com.kh.mvc.member.model.service;

import java.sql.Connection;

// 정적 메소드 호출 시 클래스 이름 생략 가능 static 추가 .getConnection 추가
//	> 이를 통해 JDBCTemplate.getConnection;을 getConnection이라고만 작성해서 사용할 수 있다.
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import  com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

/*230210 2교시 MVC 로그인 기능 구현*/

// Service : 실제로 비즈니스 로직을 처리하는 오브젝트
public class MemberService {

	public Member login(String userId, String userPwd) {

// 220210 6교시 JDBC 템플릿을 통해 MemberDao.java에서 중복되는 코드들 하나로 처리하기
		
		Member member = null;
		
		// JDBC템플릿 커넥션으로 받아오기
		Connection connection = /* JDBCTemplate. */getConnection();
				// 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		
		member = new MemberDao().findMemberById(connection, userId);
		
		// 커넥션 클로즈 (JDBC템플릿을 사용해서)
		/* JDBCTemplate. */close(connection);
			// 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		
		
		if(member == null || !member.getPassword().equals(userPwd)) {
			// member가 null이거나 패스워드가 사용자가 입력해준 패스워드와 다르다면 null 리턴, 아니라면(if문 나와서) member 리턴
			
			return null;
		}
		
		return member;
		
	}
	
// 230210 4교시 회원가입 기능 구현

	public int save(Member member) {

		int result = 0;

// 220210 6교시 JDBC 템플릿을 통해 중복되는 코드들 하나로 처리하기		
		Connection connection = getConnection();
		
		//커밋롤백 서비스에서 처리
		result = new MemberDao().insertMember(connection, member);
				// change method 'insertMember' add parameter connection으로 에러 처리
		
		if(result > 0) {
			/* JDBCTemplate. */commit(connection);
			// 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		} else {
			/* JDBCTemplate. */rollback(connection);
		}
		
		close(connection);
		
		return result;
		
	}

}
