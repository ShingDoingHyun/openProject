package com.bitcamp.op.member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.op.member.model.MemberInfo;
import com.bitcamp.op.member.service.MemberLoginService;

@Controller
public class MemberLoginController {

	@Autowired
	MemberLoginService memberLoginService;

	@RequestMapping("/memberLoginForm")
	public ModelAndView memberLoginForm() {
		String viewName = "member/member_login_form";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		return modelAndView;
	}

	@RequestMapping(value = "/memberLogin", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, @RequestParam("memberId") String memberId,
			@RequestParam("memberPassword") String memberPassword, Model model) throws SQLException {

		String id = request.getParameter("memberId");
		String pw = request.getParameter("memberPassword");

		boolean result = memberLoginService.loginMember(request, id, pw);
		
		if (result) {
			return "index";
		} else {
			return "index";
		}

	}

	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public String memberJoin(MemberInfo memberInfo, Model model) {

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
