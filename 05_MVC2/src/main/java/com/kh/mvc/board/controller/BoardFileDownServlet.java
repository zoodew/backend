package com.kh.mvc.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "boardFileDown", urlPatterns = { "/board/fileDown" })
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

// 230216 3교시 첨부파일 다운로드하기
    public BoardFileDownServlet() {
    }

// 230216 3교시 첨부파일 다운로드하기
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String oname = request.getParameter("oname");
    	String rname = request.getParameter("rname");
    	
    	System.out.println(oname + ", " + rname);
    	
    	// 1. 클라이언트로 전송할 파일의 경로와 파일명을 가져온다.
    													// 경로를 알아야 물리적인 경로에 있는 파일을 가져올 수 있다.
    	String filePath = getServletContext().getRealPath("/resources/upload/board/" + rname);
    	
    	System.out.println(filePath);
    	
    	// 2. 물리적인 경로에 저장되어있는 파일을 메모리로 가져온다.
    	File downFile = new File(filePath);
    	
    	System.out.println(downFile.exists());

    	
// 230216 4교시 첨부파일 다운로드하기
    	// 클라이언트를 읽어서 출력 할 수 있게 3 4번 함
    	// 3. 메모리로 가져온 파일에 입력 스트림을 생성
			/*
			 * new BuffuerdInputStream(); // BuffuerdInputStream 파일을 모아올 때 버퍼에 모아놨다가 한 번에 가져오는 보조스트림. 성능향상. 단독으로 사용 불가 기반 스트림을 같이 써줘야함
			 * new FileInputStream(downFile);
			 */
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
    	
    	// 4. 클라이언트로 내보낼 출력 스트림 생성
    													// BufferedOutputStream 는 response오브젝트에 있는 거 가져오기만 한 것
    	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
    	
    	// 5. 브라우저별 인코딩 처리 한글 에러 처리
    	String downName = null;
    	
    	// MSIE 외의 브라우저
    								// getBytes() 문자열을 바이트로 가져오는 메소드
    	downName = new String(oname.getBytes("UTF-8"), "ISO-8859-1");
    							// ISO-8859-1로 변환해서 인코딩하고 파일명을 내려줘야함
    	
    	// IE
    	
    	// 깃 공유 . IE는 참고만 
    	
    	
    	
    	// 6. 응답 메시지 작성
    		// setContentType은 자주 써서 메소드로 빼놓은 거고 다른 것들은 다 sethaeder()로 헤더값 설정해야함.
    		// mime 타입 application/octet-stream 모든 종류의 2진 데이터를 뜻한다.
    	response.setContentType("application/octet-stream");
    	// 다운 받은 파일 originalFileName으로 저장되게 만들기
    	response.setHeader("Content-Disposition", "attachment;filename=" + downName);
    		//sethaeder()  ????????????????
    	
    	
    	// 7. 파일을 클라이언트로 출력(전송)하기
    	int read = -1;
    	
    	while((read = bis.read()) != -1) {	// -1이면 데이터가 없다는 뜻 그때까지 bos.write(read); 반복
    		bos.write(read);
    		
    	}
    	
    	bos.close();
    	bis.close();
    	
	}

}
