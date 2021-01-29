package com.spring.board.common;

public class Utils {

	public static String myViewResolver(String fileNm) {
		return "/WEB-INF/views/" + fileNm + ".jsp";
	}
}
