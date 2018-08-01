package com.bitcamp.op.member.service;


import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.dao.MemberDao;



public class MemberDeleteService {
	
	@Autowired
	JdbcTemplateMemberDao memberDao;

	public int deleteMember(String id) {
		return memberDao.deleteMember(id); 
	}
	

	
}
