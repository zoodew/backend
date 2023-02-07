package com.kh.el.vo;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// 롬복 사용 어노테이션


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

//@Data					// 모든 거 다 만들기
//@AllArgsConstructor	// 모든 필드를 매개변수로 갖는 생성자를 만들거야
//@NoArgsConstructor	// 기본생성자만 만들거야 클래스가 아닌 필드에 붙이기 불가
//@Getter				// 게터 생성 클래스에도 필드에도 붙이는 것 가능
//@Setter				// 세터 생성 클래스에도 필드에도 붙이는 것 가능
//@ToString				// 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴 하는 것


public class Student {

//	230206 7교시 kh-study-cloud/backend 03_EL_JSTL	
	
	private String name;
	
	private int age;
	
	private int math;
	
	private int english;
}
