package com.kh.mvc.board.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;
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
// 230216 8교시 한 번 조회한 회원은 조회수 처리에 포함되지 않게 만들기  getBoardByNo(int no)를 getBoardByNo(int no, boolean hasRead)로 변경
	public Board getBoardByNo(int no, boolean hasRead) {
		Board board = null;
		Connection connection = getConnection();
		
		board = new BoardDao().findBoardByNo(connection, no);
		
// 230216 7교시 게시글 조회수 증가 로직
// 230216 8교시 한 번 조회한 회원은 조회수 처리에 포함되지 않게 만들기 if문 추가
		if (board != null && !hasRead) {	// 230216 8교시 읽지 않았어야만 if문 안의 게시글 증가 로직 실행되게 if 문 걸어줌
			
			int result = new BoardDao().updateReadCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		
		}
		
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

//  230216 2교시 게시글 삭제하기
	public int delete(int no) {
		// 반환하는 타입이 정수값이니 정수값 result 생성
		int result = 0;
		
		// 업데이트를 서비스에서 직접 실행하지 않고 데이터 액세스 커넥션을...? 통해
		Connection connection = getConnection();
		
		// 쿼리문 수행할 영향받은 오브젝트 개수를 값으로 받아옴
		result = new BoardDao().updateStatus(connection, no, "N");
				// BoardDao의 updateStatus()에서 
		
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		// return 전 커넥션 클로즈
		close(connection);		
		
		return result;
	}


	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		// insert 작업 DAO로 넘기기
		result = new BoardDao().insertReply(connection, reply);
		
		if( result > 0 ) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

}
