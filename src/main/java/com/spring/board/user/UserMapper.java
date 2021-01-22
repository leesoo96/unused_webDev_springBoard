package com.spring.board.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.board.model.UserEntity;

@Mapper
public interface UserMapper {
// 필요한 서비스들을 나열할 수 있기위해서 인터페이스를 만든다 (확장성과 유연성을 위해서)
	
//	UserMapper.xml에 있는 메소드 
	List<UserEntity> selUserList();
	
	void insert_User(UserEntity param);
}
