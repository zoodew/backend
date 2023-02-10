package com.kh.mvc.common.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


							// 230210 5교시 회원가입기능 구현
							// JDBC 템플릿을 통해 MemberDao.java에서 중복되는 코드들 하나로 처리하기

public class JDBCTemplate {
	// 정적 메소드라 getConnection으로 바로 커넥션 호출 가능
	public static Connection getConnection() {
		Connection connection = null;
		Properties properties = new Properties();
		String filePath = JDBCTemplate.class.getResource("./driver.properties").getPath();
		
//		System.out.println(filePath);
		
		try {
			// 파일를 읽어옴
			properties.load(new FileReader(filePath));
		
			Class.forName(properties.getProperty("db.driver"));
			
			// 커넥션 얻어옴
			connection = DriverManager.getConnection(
					properties.getProperty("db.url"), 		// driver.properties에 있는 db.url를 읽어옴
					properties.getProperty("db.username"), 
					properties.getProperty("db.password")
			);
			
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	// 커넥션 받아서 커밋하게 만들어둠
	public static void commit(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {				
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 커넥션 받아서 롤백하게 만들어둠
	public static void rollback(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {				
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 커넥션 받아서 클로즈하게 만들어둠
	public static void close(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 스테이트먼트 받아서 클로즈하게 만들어둠
	public static void close(Statement statement) {
		try {
			if(statement != null && !statement.isClosed()) {				
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 리절트셋 받아서 클로즈하게 만들어둠
	public static void close(ResultSet resultSet) {
		try {
			if(resultSet != null && !resultSet.isClosed()) {				
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}