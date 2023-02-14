package com.kh.mvc.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	
// 230214 1교시 게시글 목록 가져오기 각 게시글을 리스트에 담기

	private int no;
	
	private int boardNo;
	
	private int writerNo;
	
	private String writerId;
	
	private String content;	
	
	private Date createDate;
	
	private Date modifyDate;
}