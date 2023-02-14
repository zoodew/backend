package com.kh.mvc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.board.model.vo.Board;
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
			// 셀렉해서 데이터가 업으면 null이 아닌 빈 list 리턴, 데이터가 있으면 그 데이터로 리스트 생성해 리턴
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
		
		String query = "UPDATE BOARD SET TITLE=?,CONTENT=?,MODIFY_DATE=SYSDATE WHERE NO=?";
						// 깃 backend/TableScripts.sql의 144행 복붙
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
		

	}

}
