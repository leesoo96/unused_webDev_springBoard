package com.spring.board.common;

import java.util.List;

import com.spring.board.model.ManageBoardEntity;

public class Const {

	public static final String LOGINUSER = "loginUser";
	
	// 메모리 영역에 한번만 할당되고 계속 유지된다
	public static List<ManageBoardEntity> menuList = null;
}
