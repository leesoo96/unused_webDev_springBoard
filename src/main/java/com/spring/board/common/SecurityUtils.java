package com.spring.board.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.spring.board.model.UserEntity;

public class SecurityUtils {

//		true: 로그인 상태, false:  로그아웃 상태
		public static boolean isLogin(HttpSession session) {
			return getLoginUser(session) != null;
		}
		
		public static UserEntity getLoginUser(HttpSession session) {
			return (UserEntity) session.getAttribute(Const.LOGINUSER);
		}
		
		public static int getLoingUserPk(HttpSession session) {
			UserEntity loginUser = getLoginUser(session);
			return loginUser == null ? 0 : loginUser.getI_user();
		}
		
		public static String genSalt() {
			return BCrypt.gensalt();
		}

		public static String hashPassword(String pw, String salt) {
			return BCrypt.hashpw(pw, salt);
		}
}
