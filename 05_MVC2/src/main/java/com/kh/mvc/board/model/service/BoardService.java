package com.kh.mvc.board.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.jdbc.JDBCTemplate;
import com.kh.mvc.common.util.PageInfo;

//import static com.kh.mvc.common.jdbc.JDBCTemplate.*;
									// * > JDBCTemplate 밑의 모든 클래스
									// ctrl shift o 로 무슨 클래스 쓰는지 상세하게 나타낼 수 있음.
public class BoardService {

// 23021 1교시 게시판 구현 게시글 목록	
	public int getBoardCount() {
		int count = 0;
		Connection connection = /* JDBCTemplate. */getConnection();
								// 5행
		
		count = new BoardDao().getBoardCount(connection);
		
		/* JDBCTemplate. */close(connection);
		
		return count;
	}

	
// 230214 1.2교시 게시글 목록 가져오기 각 게시글을 리스트에 담기
	public List<Board> getBoardList(PageInfo pageInfo) {
		// public List<Board> 리스트를 리턴하니까 List 생성
		List<Board> list = null;
		
		Connection connection = getConnection();
		
		list = new BoardDao().findAll(connection, pageInfo);
		
		close(connection);

		return list;
	}


// 230214 3교시 게시판 제목 클릭시 나오는 상세 페이지 기능 만들기
	public Board getBoardByNo(int no) {
		Board board = null;
		Connection connection = getConnection();
		
		board = new BoardDao().findBoardByNo(connection, no);
		
		
		close(connection); // 리턴 전 클로즈
		
		return board;
		
	}


// 230214 5.6교시 파일업로드 JDBC로 insert작업 성공하면 커밋 실패하면 롤백
	public int save(Board board) {
		
		int result = 0;
		Connection connection = getConnection();
		
// 230214 8교시 수정한 내용으로 게시글이 바뀌게 만들기
		if(board.getNo() > 0) {
			// update 작업
			result = new BoardDao().updateBoard(connection, board);
		} else {
			// insert 작업
			result = new BoardDao().insertBoard(connection, board);
		}
		
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
			
		close(connection);
		
		return result;
	}

}
