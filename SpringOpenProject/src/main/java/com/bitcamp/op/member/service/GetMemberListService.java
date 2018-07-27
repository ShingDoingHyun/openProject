package com.bitcamp.op.member.service;


import java.sql.Connection;

import com.bitcamp.op.member.dao.MemberDao;
import com.bitcamp.op.member.model.MemberInfoList;


public class GetMemberListService {

	MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberInfoList getMessageList(Connection conn, int pageNumber){
		return null;
		
	}

	public MemberInfoList getMessageList(Connection conn){
		return null;
		
	}

}
