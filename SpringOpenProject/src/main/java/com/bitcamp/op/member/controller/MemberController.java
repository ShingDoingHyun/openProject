package com.bitcamp.op.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.op.member.model.MemberInfo;

@Controller
public class MemberController {
	@RequestMapping("/memberLoginForm")
	public ModelAndView memberLoginForm() {
		String viewName = "member/member_login_form";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		return modelAndView;
	}
	
	@RequestMapping(value="/memberLogin", method=RequestMethod.POST)
	public String memberLogin(MemberInfo memberInfo, Model model) {
	
		return "index";
	}	
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberJoin(MemberInfo memberInfo, Model model) {
		System.out.println(memberInfo);
		return "index";
	}	

	@RequestMapping("/memberJoinAgree")
	public ModelAndView memberJoinAgree() {
		String viewName = "member/member_join_agree";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		return modelAndView;
	}

	@RequestMapping("/memberJoinForm")
	public ModelAndView memberJoinForm() {
		String viewName = "member/member_join_form";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		return modelAndView;
	}
}
