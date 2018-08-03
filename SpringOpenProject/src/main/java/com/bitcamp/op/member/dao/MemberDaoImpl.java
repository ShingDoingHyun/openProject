package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.model.MemberInfo;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(Connection conn, MemberInfo memberInfo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into member values (?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberInfo.getUserid());
			pstmt.setString(2, memberInfo.getPassword());
			pstmt.setString(3, memberInfo.getName());
			pstmt.setDate(4, Date.valueOf(memberInfo.getBirthday()));
			pstmt.setBoolean(5, memberInfo.isGender());
			pstmt.setString(6, memberInfo.getEmail());
			pstmt.setString(7, memberInfo.getPhone());
			pstmt.setString(8, memberInfo.getPhoto());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	@Override
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from member");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	@Override
	public List<MemberInfo> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement("select userid, password, NAME, birthday, gender, email, phone, photo  from ( "
					+ " select rownum rnum, userid, password, NAME, birthday, gender, email, phone, photo from ( "
					+ " select * from member m order by m.userid desc ) where rownum <= ? "
					+ ") where rnum >= ?");

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<MemberInfo> memberList = new ArrayList<MemberInfo>();
				do {
					memberList.add(makeMemberFromResultSet(rs));// rs�� �Ѱܼ� ó��
				} while (rs.next());
				return memberList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
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
