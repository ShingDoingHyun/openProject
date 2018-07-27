package com.bitcamp.op.member.service;


import java.sql.Connection;

import com.bitcamp.op.member.dao.MemberDao;



public class DeleteMemberService {
	
	MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void deleteMember(Connection conn, String id) {
		memberDao.deleteMember(conn, id); 
	}
	

	
}
