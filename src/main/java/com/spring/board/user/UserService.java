package com.spring.board.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.spring.board.common.Const;
import com.spring.board.common.Utils;
import com.spring.board.model.UserEntity;

// dao, service는 인터페이스와 클래스가 한 세트!
// 실질적인 로직 담당
@Service // 빈 등록
public class UserService {
	
//	자동으로 객체를 만들어주고 new()없이 사용할 수 있다 
	@Autowired
	public UserMapper mapper;
	
	public int login(UserEntity param, HttpSession session) { // 로그인
		UserEntity dbData = mapper.selUser(param);
		
		if(dbData == null) {
			return 2; // 아이디없음
		}
		
		String login_Pw = Utils.hashPassword(param.getUser_pw(), dbData.getSalt());
		
		if(!login_Pw.equals(dbData.getUser_pw())) {
			return 3; // 비밀번호 오류
		}
		
		// 보안을 위해 입력된 값 날림(세션에 저장된 비밀번호 값 제거)
		dbData.setSalt(null);
		dbData.setUser_pw(null);
											// 실수 방지를 위해 final로 생성
		session.setAttribute(Const.LOGINUSER, dbData); 
		
		return 1; // 로그인 성공
	}
		
	public int insert_User(UserEntity param) { // 회원가입
		String salt = Utils.genSalt();
		String encrypt_Pw = Utils.hashPassword(param.getUser_pw(), salt);
		
		param.setSalt(salt);
		param.setUser_pw(encrypt_Pw);
		
		return mapper.insert_User(param);
	}
}
