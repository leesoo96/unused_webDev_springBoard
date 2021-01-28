package com.spring.board.user;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.board.common.Const;
import com.spring.board.common.SecurityUtils;
import com.spring.board.model.UserEntity;

//dao, service는 인터페이스와 클래스가 한 세트!
//실질적인 로직 담당
@Service
public class UserService {	
	
//	자동으로 객체를 만들어주고 new()없이 사용할 수 있다 
	@Autowired
	private UserMapper mapper;
	
	// 로그인
	public int login(UserEntity param, HttpSession hs) {
		UserEntity dbData = mapper.selUser(param);
		if(dbData == null) { 
			return 2; // 아이디없음
		}
		String cryptLoginPw = SecurityUtils.hashPassword(param.getUser_pw(), dbData.getSalt());
		
		if(!cryptLoginPw.equals(dbData.getUser_pw())) { 
			return 3; // 비밀번호 오류
		}
		
		// 보안을 위해 입력된 값 날림(세션에 저장된 비밀번호 값 제거)
		dbData.setSalt(null);
		dbData.setUser_pw(null);
		
		// 실수 방지를 위해 final로 생성
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);		
		return 1;
	}
	
//	회원가입
	public int insUser(UserEntity param) {
		String salt = SecurityUtils.gensalt();
		String encryptPw = SecurityUtils.hashPassword(param.getUser_pw(), salt);
		
		param.setSalt(salt);
		param.setUser_pw(encryptPw);
		
		return mapper.insUser(param);
	}
}










