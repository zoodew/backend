package com.kh.ajax.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//230209 2교시 github kh-study-cloud/kh/99.보충 02.Ajax.pdf JSON

// 롬복을 통해 게터세터 기본생성자 매개변수가있는생성자 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int no;
	
	private String name;

	private int age;

	private String gender;

}
