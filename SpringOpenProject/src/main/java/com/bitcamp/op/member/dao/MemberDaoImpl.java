package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bitcamp.op.jdbc.JdbcUtil;
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
	public MemberInfo makeMemberFromResultSet(ResultSet rs) throws SQLException {
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setUserid(rs.getString("userid"));
		memberInfo.setPassword(rs.getString("password"));
		memberInfo.setName(rs.getString("name"));
		memberInfo.setBirthday(rs.getString("birthday"));
		memberInfo.setGender(rs.getBoolean("gender"));
		memberInfo.setEmail(rs.getString("email"));
		memberInfo.setPhone(rs.getString("phone"));
		memberInfo.setPhoto(rs.getString("photo"));
		return memberInfo;
	}

	@Override
	public MemberInfo selectMember(MemberInfo memberInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberInfo selectById(Connection conn, String userId) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql_select = "select * from member where userid=?";
		MemberInfo member = null;
		System.out.println(sql_select+ userId );
		try {
			pstmt = conn.prepareStatement(sql_select);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = makeMemberFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
	}

}
