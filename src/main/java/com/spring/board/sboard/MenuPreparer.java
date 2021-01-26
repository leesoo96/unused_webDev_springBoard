package com.spring.board.sboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.board.common.Const;
import com.spring.board.model.ManageBoardEntity;

@Component("menuPreparer")
public class MenuPreparer  implements ViewPreparer{

	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		List<ManageBoardEntity> menuList = mapper.selBoardList();
		
		if(Const.menuList == null) {
			System.out.println("메뉴 리스트를 가져옵니다."); // 한 번만 호출됨
			Const.menuList = mapper.selBoardList();
		}
		attributeContext.putAttribute("menuList", new Attribute(menuList), true);
	}
}
