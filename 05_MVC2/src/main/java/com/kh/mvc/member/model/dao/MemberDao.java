package com.kh.mvc.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//230210 6교시 JDBC 템플릿을 사용해 간결하게 만들기
// 그 전까지 복습 카톡에 230210 6교시 JDBC 사용 전 코드 검색해 그걸로 하기

// close메소드를 JDBCTemplate없이도 사용하고 싶어서 만든 식
import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import com.kh.mvc.member.model.vo.Member;


// 230210 6교시 JDBC 템플릿을 사용해 간결하게 만들기
public class MemberDao {

	public Member findMemberById(Connection connection, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;					// 사용자가 입력한 아이디(ex admin2)로 조회할 수 있게 만든 쿼리문
		//쿼리문 생성 statement용
		//String query = "SELECT * FROM MEMBER WHERE ID='" + userId + "' AND STATUS='Y'";
		//쿼리문 생성 preparedStatement용
		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'";
		
		try {

			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {

				member = new Member();
				
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setHobby(rs.getString("HOBBY"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				member.setModifyDate(rs.getDate("MODIFY_DATE"));
				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC템플릿으로 클로즈
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	
// 230210 4교시 회원가입 기능 구현

	public int insertMember(Connection connection, Member member) {

		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		// 밑에 코드 작성 전에 클래스 생성 후 서버 작동시켜서 console창에 0이 뜨는지 확인하기 연결됐나 확인코스
		
		try {
			pstmt = connection.prepareStatement(query);
			
			// 쿼리문 실행 전 query문의 ?들에 값 넣어주기
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getHobby());
			

			result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	/*
	 * 서버 돌리고 회원가입폼 입력하고 가입시켜서 DB 켜서 제대로 들어있나 확인, 작성한 아이디 비번으로 로그인 가능한지도 봐보기
	 * 하고 DB에서 행 삭제 후(커밋 드럼통 초록모양 F11)까지 해주기
	 * 
	 * 서버 돌리고 회원가입폼 입력 후 가입했을 때
	 * 
	 * java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (WEB.SYS_C007287) violated
	 * 이런 오류가 나면 인서트 된 값 또 똑같이 넣으려고 해서 나는 것.
	 * 
	 * unique constraint 유니크 제약조건은 똑같은 값 못들어 가는데 같은 값을 또 입력하려해서
	 * DB에 이미 똑같은 값이 들어가 있는지 확인해보기
	 */
}
