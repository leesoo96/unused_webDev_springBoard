package com.spring.board.common;

import org.mindrot.jbcrypt.BCrypt;

public class Utils {
	
//	각 jsp파일 페이지 표시를 위한 메소드 
	public static String myViewResolver(String fileNm) {
		return "/WEB-INF/views/" + fileNm + ".jsp";
	}
}
