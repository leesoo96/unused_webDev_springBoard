package com.spring.board.sboard;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.sboard.BoardService;
import com.spring.board.common.Const;
import com.spring.board.common.SecurityUtils;
import com.spring.board.model.BoardCmtDomain;
import com.spring.board.model.BoardCmtEntity;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/home") 
	public void home() {}
	
	@GetMapping("/list") 
	public void list(Model model, BoardDTO p) {
		model.addAttribute(Const.KEY_LIST, service.selBoardList(p));
	}
	
	@GetMapping("/reg") 
	public String reg() {
		return "board/regmod";
	}
	
	@PostMapping("/reg")  
	public String reg(BoardEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.insBoard(p);
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@GetMapping("/detail") 
	public void detail(BoardDTO p, HttpSession hs, Model model) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
	}
		
	@ResponseBody 
	@DeleteMapping("/del/{i_board}") 
	public Map<String, Object> del(BoardDTO p, HttpSession hs) {		
		System.out.println("i_board : " + p.getI_board());
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", service.delBoard(p));
		return map;
	}
	
	@GetMapping("/mod")
	public String mod(BoardDTO p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
		return "board/regmod";
	}
	
	@PostMapping("/mod") 
	public String mod(BoardEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.updBoard(p);
		
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	// ---------CMT---------------------------------------------------------------
	@ResponseBody
	@PostMapping("/insCmt") 
	public Map<String, Object> insCmt(@RequestBody BoardCmtEntity p
			, HttpSession hs) {
		
		System.out.println("i_board : " + p.getI_board());
		System.out.println("ctnt : " + p.getCtnt());
		
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put(Const.KEY_RESULT, service.insCmt(p));
		
		return returnValue;
	}
	
	@ResponseBody
	@GetMapping("/cmtList")
	public List<BoardCmtDomain> selCmtList(BoardCmtEntity p, HttpSession hs){
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		return service.selCmtList(p);
	}
	
	@ResponseBody
	@GetMapping("/cmtList")
	public Map<String, Object> delCmt(BoardCmtEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put(Const.KEY_RESULT, service.delCmt(p));
		
		return returnValue;
	}
}
