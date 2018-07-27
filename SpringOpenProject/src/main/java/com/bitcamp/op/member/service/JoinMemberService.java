package com.bitcamp.op.member.service;

import java.sql.Connection;

import com.bitcamp.op.member.dao.MemberDao;
import com.bitcamp.op.member.model.MemberInfo;

public class JoinMemberService {
	
	MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void joinMember(Connection conn, MemberInfo memberInfo) {
		memberDao.insertMember(conn, memberInfo);
	}
	
	
}
