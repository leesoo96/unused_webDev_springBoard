package com.spring.board.sboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import com.spring.board.model.ManageBoardEntity;

@Component("menuPreparer")
public class MenuPreparer  implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		System.out.println("--menuPreparer--");
		
		ManageBoardEntity menus = new ManageBoardEntity();
		menus.setTyp(1);
		menus.setNm("일상/잡담");
		
		List<ManageBoardEntity> menuList = new ArrayList<>();
		menuList.add(menus);
		
		attributeContext.putAttribute("menuList", new Attribute(menuList), true);
	}
}
