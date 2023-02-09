package com.kh.mvc.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.mvc.member.model.vo.Member;

public class MemberDao {

	public Member findMemberById(String userId) {
		Member member = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM MEMBER WHERE ID='admin2' AND STATUS='Y'";
		
//		DriveManager에 해당 DBMS Driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

//			getConnection() = 해당 Driver로부터 Connection instance 획득			
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WEB", "WEB");

//			createStatement() = Connection instance로부터 Statement instance 획득
			statement = connection.createStatement();
			
//			executeQuery() = SELECT구문을 실행시키는 메소드
			resultSet = statement.executeQuery(query);
			
			
			while(resultSet.next()) {		// 매개값으로 컬럼명 가져와도 되고 컬럼순번을 가져와도 됨.
				member = new Member();
				
				member.setNo(resultSet.getInt("NO"));
				member.setId(resultSet.getString("ID"));
				member.setPassword(resultSet.getString("PASSWORD"));
				member.setRole(resultSet.getString("ROLE"));
				member.setName(resultSet.getString("NAME"));
				member.setPhone(resultSet.getString("PHONE"));
				member.setEmail(resultSet.getString("EMAIL"));
				member.setAddress(resultSet.getString("ADDRESS"));
				member.setHobby(resultSet.getString("HOBBY"));
				member.setStatus(resultSet.getString("STATUS"));
				member.setEnrollDate(resultSet.getDate("ENROLL_DATE"));
				member.setModifyDate(resultSet.getDate("MODIFY_DATE"));
				
				
				System.out.println(resultSet.getInt("NO"));
				System.out.println(resultSet.getString("ID"));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//클로즈 순서는 생성의 역순으로
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}

}
