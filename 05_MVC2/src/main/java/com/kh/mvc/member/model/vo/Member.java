package com.kh.mvc.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	// DB에 있는 컬럼들 필드로 생성
	
	private int no;
	
	private String id;

	private String password;

	private String role;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String hobby;
	
	private String status;

	private Date enrollDate;
	
	private Date modifyDate;
	
}
