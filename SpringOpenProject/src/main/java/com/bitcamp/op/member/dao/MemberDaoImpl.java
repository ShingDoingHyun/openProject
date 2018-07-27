package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.bitcamp.op.member.model.MemberInfo;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(Connection conn, MemberInfo memberInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberInfo> selectList(Connection conn, int firstRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberInfo> selectList(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(Connection conn, String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberInfo memberResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
