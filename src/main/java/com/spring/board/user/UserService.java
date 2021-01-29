package com.spring.board.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.common.Const;
import com.spring.board.common.SecurityUtils;
import com.spring.board.model.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
		

	public int login(UserEntity param, HttpSession hs) {
		UserEntity dbData = mapper.selUser(param);
		if(dbData == null) { 
			return 2;
		}
		String cryptLoginPw = SecurityUtils.hashPassword(param.getUser_pw(), dbData.getSalt());
		
		if(!cryptLoginPw.equals(dbData.getUser_pw())) { 
			return 3; 
		}

		dbData.setSalt(null);
		dbData.setUser_pw(null);
		
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);
		
		return 1; 
	}
	
	public int insUser(UserEntity param) {
		String salt = SecurityUtils.gensalt();
		String encryptPw = SecurityUtils.hashPassword(param.getUser_pw(), salt);
		
		param.setSalt(salt);
		param.setUser_pw(encryptPw);
		
		return mapper.insUser(param);
	}
}
