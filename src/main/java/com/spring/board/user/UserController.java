package com.spring.board.user;

import javax.servlet.http.HttpServletRequest;
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
	
	@GetMapping("/login") // 단순 로그인 페이지 표시 
	public void login() {}
	
	@PostMapping("/login") // 로그인
	public String loginProc(UserEntity param, HttpSession session) {
		int result = service.login(param, session);
		
		if(result == 1) {
			return "redirect:/board/home";
		}
		
		return null;
	}
	
	@RequestMapping("/join") 
	public void join() {} // 단순 회원가입 페이지 표시 
	
	@PostMapping("/join") // 회원가입
	public String join(UserEntity param) { 
		service.insert_User(param);
		
		return "redirect:/user/login";
	}
}
