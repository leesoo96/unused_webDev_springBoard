package com.spring.board.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.common.Utils;
import com.spring.board.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
//	service의 기능들을 쓸 수 있다
	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public void login(Model model) {
		
		model.addAttribute("list", service.selUserList());
	}
	
	@PostMapping("/login")
	public String loginProc(UserEntity param, HttpSession session) {
//		로그인 미완
		return "redirect:/";
	}
	
	@RequestMapping("/join") 
	public void join(Model model) {} // 단순 페이지 표시 
	
	@PostMapping("/join")
	public String join(UserEntity param) { 
		service.insert_User(param);
		return "redirect:/user/login";
	}
}
