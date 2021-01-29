package com.spring.board.user;

import org.apache.ibatis.annotations.Mapper;

import com.spring.board.model.UserEntity;

@Mapper
public interface UserMapper {

	UserEntity selUser(UserEntity param);
	
	int insUser(UserEntity param);
}
