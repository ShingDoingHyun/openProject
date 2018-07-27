package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.bitcamp.op.member.model.MemberInfo;


public interface MemberDao {

	public int insertMember(Connection conn, MemberInfo memberInfo);

	public int selectCount(Connection conn);

	public List<MemberInfo> selectList(Connection conn, int firstRow, int endRow);

	public List<MemberInfo> selectList(Connection conn);

	public int deleteMember(Connection conn, String userid);

	MemberInfo makeMemberFromResultSet(ResultSet rs) throws SQLException;
	
	public MemberInfo selectMember(MemberInfo memberInfo);

	public MemberInfo selectById(Connection conn, String userId) throws SQLException;


	
	

}
