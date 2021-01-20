package com.spring.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("page","/WEB-INF/views/user/login.jsp");
		
		return "temp/basic_temp";
	}
	
	@RequestMapping("/join")
	public String join(Model model) {
		model.addAttribute("page","/WEB-INF/views/user/join.jsp");
		
		return "temp/basic_temp";
	}
}
