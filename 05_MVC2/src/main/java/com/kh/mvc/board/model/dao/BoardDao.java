package com.kh.mvc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.common.util.PageInfo;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

public class BoardDao {

// 23021 1교시 게시판 구현 게시글 목록
	public int getBoardCount(Connection connection) {
		int count = 0;	// public int 정수값 리턴하니까 정수값 변수만듦
		// 쿼리문 수행시킬 object 필요. 참조변수 하나 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM BOARD WHERE STATUS='Y'";
		// 쿼리문 수행하면 seletct문의 결과는 항상 resultset 리턴. > ResultSet 변수 하나 만들기 ResultSet rs = null.
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 값이 있는지 확인하고 꺼내오는 작업
			if(rs.next()/*가져올 데이터가 있냐 묻는 것. true false리턴*/) {
				count = rs.getInt(1); // 1번 컬럼의 값 가져오겠다.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* JDBCTemplate. */close(rs);
			/* JDBCTemplate. */close(pstmt);
		}
				
		return count;	// 얻어온 count 수 리턴 count = rs.getInt(1);
		
	}

	
// 230214 2교시 게시글 목록 가져오기 각 게시글을 리스트에 담기	
	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		List<Board> list = new ArrayList<>();
			// 셀렉해서 데이터가 없으면 null이 아닌 빈 list 리턴, 데이터가 있으면 그 데이터로 리스트 생성해 리턴
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
					+ "FROM ("
					+    "SELECT ROWNUM AS RNUM, "
					+           "NO, "
					+ 			"TITLE, "
					+ 			"ID, "
					+ 			"CREATE_DATE, "
					+ 			"ORIGINAL_FILENAME, "
					+  			"READCOUNT, "
					+     		"STATUS "
					+ 	 "FROM ("
					+ 	    "SELECT B.NO, "
					+ 			   "B.TITLE, "
					+  			   "M.ID, "
					+ 			   "B.CREATE_DATE, "
					+ 			   "B.ORIGINAL_FILENAME, "
					+ 			   "B.READCOUNT, "
					+ 	   		   "B.STATUS "
					+ 		"FROM BOARD B "
					+ 		"JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					+ 		"WHERE B.STATUS = 'Y' ORDER BY B.NO DESC"
					+ 	 ")"
					+ ") WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());// .getStartList() 현재 페이지의 시작 리스트 번호 (1페이지는 1 2페이지은 11 3페이지는 21...)
			pstmt.setInt(2, pageInfo.getEndList());  // .getEndList() 현재 페이지의 마지막 리스트 번호 (1페이지는 10 2페이지은 20 3페이지는 20...)
					// WHERE RNUM BETWEEN ? and ?의 ? 정수값 받아서 setInt(), 첫 번째 물음표에는 pageInfo.getStartList() 들어가라...
			rs = pstmt.executeQuery();
			
			// 반복문
			while (rs.next()) {
				Board board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				
				//열 개가 조회되면 열 개의 데이터를 리스트에 담아줌, 조회되는 게 없으면 빈 리스트 리턴
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}


// 230214 3교시 게시판 제목 클릭시 나오는 상세 페이지 기능 만들기
	public Board findBoardByNo(Connection connection, int no) {
	// 데이터 조회하는 거 만들기
		Board board = null;
		
		PreparedStatement pstmt = null; // 참조변수받을
		ResultSet rs = null;			// 결과값받을
		
		String query = "SELECT  B.NO, "
							+ "B.TITLE, "
							+ "M.ID, "
							+ "B.READCOUNT, "
							+ "B.ORIGINAL_FILENAME, "
							+ "B.RENAMED_FILENAME, "
							+ "B.CONTENT, "
							+ "B.CREATE_DATE, "
							+ "B.MODIFY_DATE "
						+ "FROM BOARD B "
						+ "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
						+ "WHERE B.STATUS = 'Y' AND B.NO=?";
			// 깃 backend/TableScripts.sql의 151 ~ 154 행 복사해서 oracle가서 붙여넣고 실행
			// String query 에도 복붙, 후 위와같이 수정
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);	// 물음표 넣어줌
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("ID"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("CONTENT"));
// 230216 5교시 댓글 조회
				// dao에서 게시글 상세 조회를 할 때 그 게시글에 관련된 댓글까지 조회될 수 있게 수정
				board.setReplies(this.getRepliesByNo(connection, no));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return board;
	}


// 230214 5.6교시 파일업로드 JDBC로 insert작업 성공하면 커밋 실패하면 롤백
	public int insertBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		// 깃 backend/TableScripts.sql의 141행 복붙
		
		try {
			pstmt = connection.prepareStatement(query);	// query문 받을 prepareStatement 생성
			
			pstmt.setInt(1, board.getWriterNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getOriginalFileName());
			pstmt.setString(5, board.getRenamedFileName());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

// 230214 8교시 수정한 내용으로 게시글이 바뀌게 만들기
	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE BOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
						// 깃 backend/TableScripts.sql의 144행 복붙
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setInt(5, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
		

	}
	
//  230216 3교시 게시글 삭제하기
	public int updateStatus(Connection connection, int no, String status) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 쿼리문 만들고 쿼리문을 실행시키기 전에 물음표 값을 채워넣어 세팅해 둘 수 잇음. 물음표 = 위치홀더
		String query = "UPDATE BOARD SET STATUS=? WHERE NO=?";	// 깃 backend/TableScripts.sql의 144행 복붙
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);	// db의 상태값 Y N 
			pstmt.setInt(2, no);
		
			// 영향받은 행의 개수를 result로 리턴
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);	// pstmt 변수 선언 try 구문 안에서 하지 않는 이유 try 구문이 끝나면 변수 소멸됨 close(pstmt)를 못해줌
		}
		
		return result;
		
		//웹에서는 삭제되나 DB에서는 행이 삭제되지 않고 STATUS 값을 N으로 바꿔줌
	}
	
	
// 230216 5교시 댓글 조회
	// 게시글을 조회할 때 댓글도 함께 조회될 수 있도록 만들기
			// 여러 댓글이 있을 수 있으니 List
	public List<Reply> getRepliesByNo(Connection connection, int no) {
		List<Reply> replies = new ArrayList<>();
		// 셀렉해서 데이터가 없으면 null이 아닌 빈 list 리턴, 데이터가 있으면 그 데이터로 리스트 생성해 리턴
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String query = 
	            "SELECT R.NO, "
	                + "R.BOARD_NO, "
	                + "R.CONTENT, "
	                + "M.ID, "
	                + "R.CREATE_DATE, "
	                + "R.MODIFY_DATE "
	           + "FROM REPLY R "
	           + "JOIN MEMBER M ON(R.WRITER_NO = M.NO) "
	           + "WHERE R.STATUS='Y' AND BOARD_NO=? "
	           + "ORDER BY R.NO DESC";
		// backend/Tablescripts.sql의 252~256 복붙
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			// executeQuery > SELECT 구문 수행할 때 사용. SELECT문 수행하면 resultSet 수행.
			// executeUpdate > INSERT, UPDATE, DELETE 수행할 때 사용
			rs = pstmt.executeQuery();
			
			//여러 댓글 나올 수 있게 while 문
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setNo(rs.getInt("NO"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setWriterId(rs.getString("ID"));
				reply.setCreateDate(rs.getDate("CREATE_DATE"));
				reply.setModifyDate(rs.getDate("MODIFY_DATE"));
				
				replies.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replies;
	}

// 230216 7교시 댓글 작성해 등록하기
	public int insertReply(Connection connection, Reply reply) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
						// 149행
		
		try {
			pstmt = connection.prepareStatement(query);

			// 쿼리 수행 전 물음표 값 넣기
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getWriterNo());
			pstmt.setString(3, reply.getContent());
			
			
			// 쿼리문 수행해서 그 값을 result에 담아줌
							// executeQuery > SELECT 구문 수행할 때 사용. SELECT문 수행하면 resultSet 수행.
							// executeUpdate > INSERT, UPDATE, DELETE 수행할 때 사용
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
// 230216 7교시 게시글 조회수 증가 로직
	public int updateReadCount(Connection connection, Board board) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE BOARD SET READCOUNT=? WHERE NO=?";
					// 138행
		
		try {
			pstmt = connection.prepareStatement(query);
			
			// 쿼리 수행 전 물음표 값 넣기
			board.setReadCount(board.getReadCount() + 1);		// 기존 readCount에 1 더해서 그 값을 받음
			
			pstmt.setInt(1, board.getReadCount());
			pstmt.setInt(2, board.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
