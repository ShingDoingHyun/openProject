package com.bitcamp.op.member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitcamp.op.member.model.MemberInfo;
import com.bitcamp.op.member.service.MemberDeleteService;
import com.bitcamp.op.member.service.MemberJoinService;
import com.bitcamp.op.member.service.MemberListService;
import com.bitcamp.op.member.service.MemberLoginService;

@Controller
public class MemberController {

	@Autowired
	MemberLoginService memberLoginService;
	
	@Autowired
	MemberJoinService memberJoinService;
	
	@Autowired
	MemberListService memberListService;
	
	@Autowired
	MemberDeleteService memberDeleteServiece;

	@RequestMapping("/member/memberLoginForm")
	public String memberLoginForm(@RequestParam(value="userId", defaultValue="") String userId, Model model) {

		model.addAttribute("userId", userId);
		return "member/member_login_form";
	}

	@RequestMapping(value = "/member/memberLogin", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, @RequestParam("memberId") String memberId,
			@RequestParam("memberPassword") String memberPassword, RedirectAttributes redirectAttributes) throws SQLException {

		String id = request.getParameter("memberId");
		String pw = request.getParameter("memberPassword");
 
		boolean result = memberLoginService.loginMember(request, id, pw);
		if (result) {
			return "redirect:/";
		} else {
			redirectAttributes.addAttribute("userId", memberId);
			return "redirect:/member/memberLoginForm";
		}

	}
	
	@RequestMapping(value = "/member/memberLogout")
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}


	@RequestMapping("/member/memberJoinAgree")
	public String memberJoinAgree() {
	
		return "member/member_join_agree";
	}

	@RequestMapping("/member/memberJoinForm")
	public String memberJoinForm() {
	
		return "member/member_join_form";
	}
	
	@RequestMapping("/member/memberIdCheck")
	@ResponseBody
	public String memberIdCheck(@RequestParam(value="userid") String userid){
		
		System.out.println("오긴 왔는데"+userid);
		String result = Integer.toString(memberJoinService.selectMemberById(userid));
		System.out.println(result+"결과");
		return result;
	}
	
	
	
	@RequestMapping(value = "/member/memberJoin", method = RequestMethod.POST)
	public String memberJoin(MemberInfo memberInfo, Model model, HttpServletRequest request) throws Exception {
		
		int result = memberJoinService.joinMember(memberInfo, request);

		return "redirect:/";
	}
	
	
	@RequestMapping("/member/memberMypage")
	public String memberMypage() {
		
		return "member/member_mypage";
	}
	
	@RequestMapping("/member/memberList")
	public String memberMypage(Model model, @RequestParam(value="page", defaultValue="1") int pageNum) throws SQLException {
		
		model.addAttribute("memberInfos", memberListService.getMessageList(pageNum));
		return "member/member_list";
	}

	@RequestMapping("/member/memberDelete")
	public String memberDelete(Model model, @RequestParam(value="userid") String userid){
		
		model.addAttribute("result", memberDeleteServiece.deleteMember(userid));
		return "redirect:/member/memberList";
	}
	

}
