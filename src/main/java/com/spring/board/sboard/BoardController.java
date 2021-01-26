package com.spring.board.sboard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/home") // home 페이지 열기
	public void home() {}
	
	@GetMapping("/list")
	public void show_List(Model model, BoardDTO param) {
		model.addAttribute("list", service.selBoardList(param));
	}
	
	@GetMapping("/reg")
	public String reg() {
		return "board/regmod";
	}
	
	@PostMapping("/reg")
	public String reg(BoardEntity param, HttpSession session) {
		
		service.insertBoard(param);
		
		return "redirect:/board/detail?i_board=" + param.getI_board();
	}
}
