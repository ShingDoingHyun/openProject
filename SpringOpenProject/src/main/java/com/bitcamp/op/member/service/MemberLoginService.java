package com.bitcamp.op.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.model.MemberInfo;

public class MemberLoginService {
//	MemberDao memberDao;
//
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
	
	@Autowired
	JdbcTemplateMemberDao memberDao;

	public boolean loginMember(HttpServletRequest request, String userId, String userPw) throws SQLException {

		boolean result = false;
		HttpSession session = request.getSession(false);
		//Connection conn = null;
//		try {
			//conn = ConnectionProvider.getConnection();

			MemberInfo memberdto = memberDao.selectById( userId);   //conn,
			
			if (memberdto != null && memberdto.isMatchPassword(userPw)) {
				session.setAttribute("loginInfo", memberdto);
				result = true;
			} 
//		} finally {
//			JdbcUtil.close(conn);
//		}

		return result;
	}


}
