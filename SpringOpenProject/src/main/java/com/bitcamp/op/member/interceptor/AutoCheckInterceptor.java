package com.bitcamp.op.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutoCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);

		if (session != null) {
			if (session.getAttribute("loginInfo") != null) {
				return true;
			}
		}

		response.sendRedirect(request.getContextPath() + "/index");

		return false;
	}

}
