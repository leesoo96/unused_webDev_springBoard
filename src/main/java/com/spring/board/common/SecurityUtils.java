package com.spring.board.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import com.spring.board.model.UserEntity;

public class SecurityUtils {

//	true: 로그인 상태, false:  로그아웃 상태
	public static boolean isLogin(HttpSession hs) {
		return getLoginUser(hs) != null;
	}
	
	public static UserEntity getLoginUser(HttpSession hs) {		
		return (UserEntity) hs.getAttribute(Const.KEY_LOGINUSER);
	}
	
	public static int getLoingUserPk(HttpSession hs) {
		UserEntity loginUser = getLoginUser(hs);
		return loginUser == null ? -1 : loginUser.getI_user();
	}
	
	public static String gensalt() {
		return BCrypt.gensalt();
	}

	public static String hashPassword(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}
	
}








