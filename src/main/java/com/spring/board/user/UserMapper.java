package com.spring.board.user;

import org.apache.ibatis.annotations.Mapper;

import com.spring.board.model.UserEntity;

@Mapper
public interface UserMapper {
// 필요한 서비스들을 나열할 수 있기위해서 인터페이스를 만든다 (확장성과 유연성을 위해서)
	
//UserMapper.xml에 있는 메소드들
	
//	유저 정보의 값을 전부 가져오기위한 메소드
	UserEntity selUser(UserEntity param); 
	
	int insUser(UserEntity param);
}

