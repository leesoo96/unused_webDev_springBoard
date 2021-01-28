package com.spring.board.common;

import java.util.List;

import com.spring.board.model.ManageBoardEntity;

public class Const {
	
//메모리 영역에 한번만 할당되고 계속 유지된다
	public static List<ManageBoardEntity> menuList = null;
	
	public static final String KEY_LOGINUSER = "loginUser";
	public static final String KEY_LIST = "list";
	public static final String KEY_DATA = "data";
	public static final String KEY_RESULT = "result";
}
