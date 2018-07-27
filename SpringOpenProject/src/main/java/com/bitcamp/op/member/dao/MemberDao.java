package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import com.bitcamp.op.member.model.MemberInfo;


public interface MemberDao {

	public int insertMember(Connection conn, MemberInfo memberInfo);

	public int selectCount(Connection conn);

	public List<MemberInfo> selectList(Connection conn, int firstRow, int endRow);

	public List<MemberInfo> selectList(Connection conn);

	public int deleteMember(Connection conn, String userid);

	public MemberInfo memberResultSet(ResultSet rs);

}
