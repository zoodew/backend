package com.kh.mvc.member.model.service;

import java.sql.Connection;

// 정적 메소드 호출 시 클래스 이름 생략 가능 static 추가 .getConnection 추가
//	> 이를 통해 JDBCTemplate.getConnection;을 getConnection이라고만 작성해서 사용할 수 있다.
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

/*230210 2교시 MVC 로그인 기능 구현*/

// Service : 실제로 비즈니스 로직을 처리하는 오브젝트
public class MemberService {

	public Member login(String userId, String userPwd) {
		/*
		 * // 220210 6교시 JDBC 템플릿을 통해 MemberDao.java에서 중복되는 코드들 하나로 처리하기
		 * 
		 * Member member = null;
		 * 
		 * // JDBC템플릿 커넥션으로 받아오기 Connection connection = JDBCTemplate. getConnection();
		 * // 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		 * 
		 * member = new MemberDao().findMemberById(connection, userId);
		 * 
		 * // 커넥션 클로즈 (JDBC템플릿을 사용해서) JDBCTemplate. close(connection); // 정적 메소드 호출 시
		 * 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		 * 
		 * 
		 * if(member == null || !member.getPassword().equals(userPwd)) { // member가
		 * null이거나 패스워드가 사용자가 입력해준 패스워드와 다르다면 null 리턴, 아니라면(if문 나와서) member 리턴
		 * 
		 * return null; }
		 * 
		 */
		
// 230213 4교시 회원가입 수정 > 중복되는 코드 정리
		
		Member member = this.findMemberById(userId);
		
		
		if(member == null || !member.getPassword().equals(userPwd)) { 			  
			 return null;
		}

		return member;
	}

// 230210 4교시 회원가입 기능 구현

	public int save(Member member) {

		int result = 0;

// 220210 6교시 JDBC 템플릿을 통해 중복되는 코드들 하나로 처리하기		
		Connection connection = getConnection();

// 230213 3교시 회원정보 수정하기		
		if (member.getNo() > 0) {
			// update 작업
			result = new MemberDao().updateMember(connection, member);
			// 에러 > 메소드 생성
		} else {
			// insert 작업
			result = new MemberDao().insertMember(connection, member);
		}

// 220210 6교시 JDBC 템플릿을 통해 중복되는 코드들 하나로 처리하기		

		// 커밋롤백 서비스에서 처리
//		result = new MemberDao().insertMember(connection, member);
		// change method 'insertMember' add parameter connection으로 에러 처리

		if (result > 0) {
			/* JDBCTemplate. */commit(connection);
			// 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		} else {
			/* JDBCTemplate. */rollback(connection);
		}

		close(connection);

		return result;

	}

// 230213 2교시 취미 체크박스 선택 안 하고 가입시 null point exception 뜨는 것 해결
	public boolean isDuplicateId(String userId) {

		/*
		 * Connection connection = getConnection();
		 * 
		 * Member member = new MemberDao().findMemberById(connection, userId);
		 * 
		 * close(connection);
		 * 
		 * return member != null; // 입력받은 아이디가 DB에 있는 아이디 데이터와 중복이면 true, 중복이 아니면 false
		 * 콘솔에 출력
		 */

// 230213 4교시 회원가입 수정 > 중복되는 코드 정리
		return this.findMemberById(userId) != null;
	}

// 230213 4교시 회원정보 수정
	public Member findMemberById(String userId) {

		Connection connection = getConnection();

		Member member = new MemberDao().findMemberById(connection, userId);

		close(connection);

		return member;

	}

// 230213 5교시 회원정보 수정 비밀번호 변경
	public int updatePassword(int no, String userPwd) {
//		return 1;
		// 밑에 작성 전 return = 1, 0로 바꿔서 제대로 작동되나 확인하기
		
		int result = 0;
		Connection connection = getConnection();		

		result = new MemberDao().updateMemberPassword(connection, no, userPwd);
		
		if (result > 0) {
			/* JDBCTemplate. */commit(connection);
			// 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		} else {
			/* JDBCTemplate. */rollback(connection);
		}

		close(connection);
		
		
		return result;
		
		
	}

	
// 230213 6교시 회원정보 수정 탈퇴
	public int delete(int no) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new MemberDao().updateMemberStatus(connection, no, "N");
		
		if (result > 0) {
			/* JDBCTemplate. */commit(connection);
			// 정적 메소드 호출 시 클래스(JDBCTemplate) 이름 생략 가능. 위의 import 수정 후 삭제
		} else {
			/* JDBCTemplate. */rollback(connection);
		}

		close(connection);
		
		
		
		
		
		return result;
	}

}
