package com.kh.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.kh.mvc.common.wrappaer.EncryptPasswordWrapper;


//230210 8교시 패스워드 암호화하여 DB에 저장하는 기능


//@WebFilter(filterName = "encrypt", urlPatterns = {"/login", "/member/enroll"})	// 필터명 지정 encrypt, 경로 지정
@WebFilter(filterName = "encrypt", servletNames =  {"login", "enroll"})	// 필터명 지정 encrypt, 경로지정 서블릿명으로 지정
public class EncryptFilter implements Filter {
       

    public EncryptFilter() {
    }



	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("암호화 필터 적용");
		// 로그인, 회원가입 할 때만 이 필터를 탐.
		// 위에서 필터를 태울 서블릿을 지정해줬기 때문에
	
		
		// request 객체의 파라미터 값은 직접 수정할 수가 없다.
		// 기본적으로 사용자가 보낸 요청 정보나 응답 객체를 임의로 수정할 수 없지만 요청, 응답을 감싸는 래퍼 클래스를 만들면 수정할 수 있다.
		EncryptPasswordWrapper wrapper = new EncryptPasswordWrapper((HttpServletRequest)request);
		
		// request 대신에 생성한 wrapper를 넘겨준다.
		chain.doFilter(wrapper, response);

		// admin2 계정은 로그인이 안 됨 암호화 된 상태여서 실습 테스트 불가능
		// 새로 회원가입해서 그걸로 DB(오라클에) 비밀번호 어떻게 저장되는지 확인하기
		
	}
	
	


}
