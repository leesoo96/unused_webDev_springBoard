package com.spring.board.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {

//	service의 기능들을 쓸 수 있다
	@Autowired
	private UserService service;
	
	@GetMapping("/login") //단순 로그인 페이지 표시 
	public void login() {}
	
	@PostMapping("/login") // 로그인
	public String loginProc(UserEntity param, HttpSession hs) {
		int result = service.login(param, hs);
		
		if(result == 1) {
			return "redirect:/board/home";
		}
		return null;
	}
	
	@RequestMapping("/join") // 단순 회원가입 페이지 표시 
	public void join() {}
	
	@PostMapping("/join") // 회원가입
	public String join(UserEntity param) {	 
		service.insUser(param);
		
		return "redirect:/user/login";
	}
}













