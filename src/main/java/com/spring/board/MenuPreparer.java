package com.spring.board;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.board.common.Const;

@Component("menuPrep")
public class MenuPreparer implements ViewPreparer{

	@Autowired
	private Common_Mapper mapper;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {		
		if(Const.menuList == null) {
			Const.menuList = mapper.selManageBoardList();
		}
        attributeContext.putAttribute("menuList", new Attribute(Const.menuList), true);		
	}
}
