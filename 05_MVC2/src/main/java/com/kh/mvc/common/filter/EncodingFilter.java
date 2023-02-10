package com.kh.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//230210 7교시 Servlet Filter 기능



/*
 * 서블릿 필터
 * 		- request, response가 서블릿이나 JSP 등에 도달하기 전에 필요한 전/후 처리 작업을 실행한다.
 * 		- FilterChain을 통해서 여러 개의 필터를 연속적으로 사용이 가능하다.
 * 
 * 서블릿 필터 등록 및 매핑
 * 		- WEB-INF/web.xml 파일에 필터를 등록해서 사용한다
 * 		- @WebFilter 어노테이션으로 필터를 등록해서 사용한다.
 * 
 */
// 앞단에서 인코딩을 해주는 필터 생성
// 서블릿을 태우기 전에 필터 먼저 생성


@WebFilter(filterName = "endoding", urlPatterns = "/*")		// "/*" > 모든 요청에 대해 필터를 태우겠다는 의미  *은 모든을 뜻함

							// implements Filter(javax.servlet.Filter) 추가.
public class EncodingFilter implements Filter{
	
	public EncodingFilter() {
	}
	

	/* alt shift soverride/implements methods */

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("인코딩 필터가 생성되어 초기화 진행");
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("서블릿 동작 전 코드 실행");
		
		//doFilter로 서블릿 꺼내오니까 그 전에 필터를 태울 수 있게 만들기 위해 앞에 코드를 작성함.
		String requestMethod = ((HttpServletRequest)request).getMethod();
		
		if(requestMethod.equals("POST")) {
			request.setCharacterEncoding("UTF-8");
			
			System.out.println(request.getCharacterEncoding() + "인코딩 완료");
			
			// POST요청에 대해서만 인코딩하고 SYSO가 뜨도록 설정해둠.
		}
		
		//다음 필터를 호출하거나 마지막 필터면 Servlet, JSP를 호출한다.
		chain.doFilter(request, response);
		
//		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("서블릿 동작 후 코드 실행");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("인코딩 필터가 소멸됨");
	}
	



	
	
}
