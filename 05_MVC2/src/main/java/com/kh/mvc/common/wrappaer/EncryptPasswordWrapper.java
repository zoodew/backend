package com.kh.mvc.common.wrappaer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.kh.mvc.common.util.EncryptUtil;


									//230210 8교시 패스워드 암호화하여 DB에 저장하는 기능


public class EncryptPasswordWrapper extends HttpServletRequestWrapper{

	public EncryptPasswordWrapper(HttpServletRequest request) {
		super(request);
	}

	
	// HttpServletRequestWrapper에서 재정의하고 싶은 메소드를 작성한다.
	@Override
	public String getParameter(String name) {
		
		String value = "";
		
		// 클라이언트가 전달하는 값 중에 userPwd 값만 암호화 처리를 하고 나머지는 정산적으로 반환하도록 구현
		if(name.equals("userPwd")) {
			value = EncryptUtil.oneWayEnc(super.getParameter(name), "SHA-256");
		} else {
			value = super.getParameter(name);
		}
				
		return value;
	}

	
}
