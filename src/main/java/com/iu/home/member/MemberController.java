package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberService.getIdCheck(memberVO);
//		int result=0; //memberVO == null
//		if(memberVO != null) {
//			result =1;
//		}
//		return result;
		
	}
	
	@GetMapping("add")
	public void setAdd(@ModelAttribute MemberVO memberVO)throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("vo",memberVO);
	}
	
	@PostMapping("add")
	public ModelAndView setAdd(ModelAndView mv,@Valid MemberVO memberVO, BindingResult bindingResult, ModelAndView modelAndView)throws Exception{
//		if(bindingResult.hasErrors()) {
//			//검증에 실패하면 회원가입하는 jsp로 forward
//			log.info("==========검증 에러 발생=========");
//			mv.setViewName("member/add");
//			return mv;
//		}

		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check==true) {
			log.info("==========검증 에러 발생=========");
			mv.setViewName("member/add");
			//=============================
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError fieldError:errors) {
				log.info("FieldError {}",fieldError);
				log.info("Field = {}", fieldError.getField());
				log.info("Message => {}", fieldError.getRejectedValue());
				log.info("Default => {}", fieldError.getDefaultMessage());
				log.info("Code => {}", fieldError.getCode());
				mv.addObject(fieldError.getField(),fieldError.getDefaultMessage());
				
				
				
				log.info("=================");
			}
			return mv;
		}
		
//		int result = memberService.setAdd(memberVO); 
		mv.setViewName("redirect:../");
		return mv;
	}
	@GetMapping("login")
	public void getLogin()throws Exception{
		
	}
//	spring security가 함
//	
//	@PostMapping("login")
//	public String getLogin(MemberVO memberVO, HttpSession session)throws Exception{
//		
//		memberVO = memberService.getLogin(memberVO);
//		
//		session.setAttribute("member", memberVO);
//		
//		return "redirect:../";
//	}
	
	@GetMapping("logout")
	public String getLogout(HttpSession session)throws Exception{
		session.invalidate();
		
		return "redirect:../";
	}

}