package com.spring.board.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.board.model.UserEntity;

public class SecurityUtils {

//	true -> 로그아웃 상태 / false -> 로그인 상태
	public static boolean isLogout(HttpServletRequest request) {
		return getLoginUser(request) == null;
	}
	// 코드 다시 고쳐야함!!!!!!!!!!!! + UserService
	public static UserEntity getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return (UserEntity)session.getAttribute("loginUser");
	}
//	------------------------------------------------------------------------
}
