package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("delete")
	public ModelAndView setDelete(HttpSession session, String pw) throws Exception{
		ModelAndView mv = new ModelAndView();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTENXT");
		Authentication authentication =context.getAuthentication();
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();

		int result = memberService.setDelete(memberVO);
		if(result>0) {
			mv.setViewName("redirect:/member/logout");
		}else {
			
		}
		return mv;
	}

	
	@GetMapping("logoutResult")
	public String socialLogout()throws Exception{
		return "redirect:../";
	}
	
	@GetMapping("mypage")
	public void getMyPage()throws Exception{
		
	}
	
	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberService.getIdCheck(memberVO);
//		int result=0; //memberVO == null
//		
//		if(memberVO != null) {
//			result =1;
//		}
//		
//		return result;
		
	}
	
	@GetMapping("add")
	public void setAdd(@ModelAttribute MemberVO memberVO)throws Exception{
		//MemberVO memberVO = new MemberVO();
		//model.addAttribute("vo", memberVO);
	}
	
	@PostMapping("add")
	public ModelAndView setAdd(@Valid MemberVO memberVO, BindingResult bindingResult, ModelAndView mv)throws Exception{
		
//		if(bindingResult.hasErrors()) {
//			//검증에 실패하면 회원가입하는 jsp로 foward
//			log.info("===== 검증 에러 발생 =====");
//			mv.setViewName("member/add");
//			return mv;
//		}
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			log.info("===== 검증 에러 발생 =====");
			mv.setViewName("member/add");
			//================================
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError fieldError:errors) {
				
				log.info("FiedError => {} ", fieldError);
				log.info("Field => {} ", fieldError.getField());
				log.info("Message => {}", fieldError.getRejectedValue());
				log.info("Default => {}", fieldError.getDefaultMessage());
				log.info("code => {} ", fieldError.getCode());
				mv.addObject(fieldError.getField(), fieldError.getDefaultMessage());
				
				log.info("==============================================");
			}
			
			return mv;
		}
		
		//int result = memberService.setAdd(memberVO); 
		
		mv.setViewName("redirect:../");
		return mv;
	}
	@GetMapping("login")
	public void getLogin(@RequestParam(defaultValue = "false", required = false) boolean error, String message, Model model)throws Exception{
		log.info("test1");
		if(error) {
			model.addAttribute("msg", "ID 또는 PW를 확인하세요");
		}
		//Controller에서 받아서 jsp로 다시 보내도 됨
	}
	@PostMapping("login")
	public String getLogin()throws Exception{
		log.info("test2");
		log.info(" ========= Login Post ===========");
		return "member/login";
	}
	
//	@GetMapping("logout")
//	public String getLogout(HttpSession session)throws Exception{
//		log.info("==== 내가만든 logout 메서드=====");
//		session.invalidate();
//		
//		return "redirect:../";
//	}

}