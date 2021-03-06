package com.bitcamp.op.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.dao.MemberDaoInterface;
import com.bitcamp.op.member.model.MemberInfo;
import com.bitcamp.op.member.model.MemberInfoList;

public class MemberListService {

/*	@Autowired
	JdbcTemplateMemberDao memberDao;*/
	
//	MemberDao memberDao;
//	
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
//	
	
	@Autowired
	SqlSessionTemplate template;
	
	private MemberDaoInterface memberDao;
	private static final int MESSAGE_COUNT_PER_PAGE = 10;


	public MemberInfoList getMessageList(int pageNumber, String searchName) throws SQLException{
		memberDao = template.getMapper(MemberDaoInterface.class);
		//Connection conn = null;

		int currentPageNumber = pageNumber > 0 ? pageNumber : 1; 
		
		//try {
			//conn = ConnectionProvider.getConnection();

			int messageTotalCount = memberDao.selectCount();
			
			
			List<MemberInfo> memberList = null;
			int firstRow = 0;
			int endRow = 0;
			if (messageTotalCount > 0) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				memberList = memberDao.selectList(endRow, firstRow, searchName);
			} else {
				currentPageNumber = 0;
				memberList = Collections.emptyList();
			}
			return new MemberInfoList(memberList, messageTotalCount, currentPageNumber, MESSAGE_COUNT_PER_PAGE,
					firstRow, endRow);
//		}finally {
//			JdbcUtil.close(conn);
//		}
	}
}
