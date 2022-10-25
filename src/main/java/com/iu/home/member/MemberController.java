package com.iu.home.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

	
	
	@GetMapping("add")
	public ModelAndView getAdd() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/add");
		return mv;
	}
	
	@GetMapping("login")
	public ModelAndView getLogin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login");
		return mv;
	}
	
//	@PostMapping("login")
	
//	@GetMapping("logout")
}
