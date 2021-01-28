package com.spring.board.sboard;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.common.Const;
import com.spring.board.common.SecurityUtils;
import com.spring.board.model.BoardCmtEntity;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/home") // home 페이지 표시
	public void home() {}
	
	@GetMapping("/list") // 각 게시판의 글 목록 읽어오기
	public void list(Model model, BoardDTO p) {
		model.addAttribute(Const.KEY_LIST, service.selBoardList(p));
	}
	
	@GetMapping("/reg") // 글쓰기 페이지 표시
	public String reg() {
		return "board/regmod";
	}
	
	@PostMapping("/reg") //글쓰기 
	public String reg(BoardEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.insBoard(p);
		
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@GetMapping("/detail") // 글읽기
	public void detail(BoardDTO p, HttpSession hs, Model model) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
	}
		
	@ResponseBody // -> 알아서 JSON형태로 저장한다
	@DeleteMapping("/del/{i_board}")	 // 글삭제
	public Map<String, Object> del(BoardDTO p, HttpSession hs) {		
		System.out.println("i_board : " + p.getI_board());
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", service.delBoard(p));
//						    key 값      value 값 
		
		return map;
	}
	
	@GetMapping("/mod") // 글수정 페이지 표시
	public String mod(BoardDTO p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
		
		return "board/regmod";
	}
	
	@PostMapping("/mod") // 글수정
	public String mod(BoardEntity p, HttpSession hs) {
//	   보안처리를 걸어줘야 악의적인 수정 등의 공격이 불가하다 
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.upBoard(p);
		
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@ResponseBody
	@PostMapping("/insCmt") //	댓글쓰기
	public Map<String, Object> insCmt(@RequestBody BoardCmtEntity p, HttpSession hs) { 
		System.out.println("i_board = " + p.getI_board() + " / ctnt = " + p.getCtnt());
		
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		Map<String, Object> return_value = new HashMap<>();
		return_value.put(Const.KEY_RESULT, service.insCmt(p));
		
		return return_value;
	}
}