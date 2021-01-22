package com.spring.board.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.spring.board.model.UserEntity;

// dao, service는 인터페이스와 클래스가 한 세트!
// 실질적인 로직 담당
@Service // 빈 등록
public class UserService {
	
//	자동으로 객체를 만들어주고 new()없이 사용할 수 있다 
	@Autowired
	public UserMapper mapper;
	
	public List<UserEntity> selUserList() {
		
		return mapper.selUserList();
	}
	
	public void insert_User(UserEntity param) {
		mapper.insert_User(param);
	}
}
