package com.spring.board.sboard;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.common.SecurityUtils;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {
//	소스 전부 수정하기!!!!!!
	@Autowired
	private BoardService service;
	
	@GetMapping("/home")
	public void home() {}
	
	@GetMapping("/list")
	public void list(Model model, BoardDTO p) {
		model.addAttribute("list", service.selBoardList(p));
	}
	
	@GetMapping("/reg")
	public String reg() {
		return "board/regmod";
	}
	
	@PostMapping("/reg")
	public String reg(BoardEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.insertBoard(p);
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@GetMapping("/detail")
	public void detail(BoardDTO p, HttpSession session, Model model) {
		p.setI_user(SecurityUtils.getLoingUserPk(session));
		model.addAttribute("data", service.selBoard(p));
	}
	
	@ResponseBody // @ResponseBody -> 알아서 JSON형태로 저장한다
//							   RESTful 방식 사용
	@GetMapping("/del/{i_board}")
	public  Map<String, Object> del(BoardDTO p, HttpSession session) {
		System.out.println("i_board = " + p.getI_board());
		
		p.setI_board(SecurityUtils.getLoingUserPk(session));
		
		Map<String, Object> map = new HashMap<String, Object>();
		//               key 값      value 값 
		map.put("result", service.delBoard(p));
		
		return map;
	}
}
