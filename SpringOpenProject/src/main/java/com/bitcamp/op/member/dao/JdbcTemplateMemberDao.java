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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.model.MemberInfo;
import com.bitcamp.op.member.model.MemberInfoList;

public class JdbcTemplateMemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public MemberInfo selectById(String userId) {

		MemberInfo resultObj = null;
		String sql = "select * from member where userid=?";

		// List<MemberInfo> result = jdbcTemplate.query(sql, new MemberRowMapper(),
		// userId);
		// resultObj = result.isEmpty() ? null : result.get(0);

		resultObj = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), userId);

		return resultObj;
	}

	public int insertMember(MemberInfo memberInfo) {

		int resultCnt = 0;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		String sql = "insert into member values (?,?,?,?,?,?,?,?)";

		
		resultCnt = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pstmt =null;
				
				pstmt = con.prepareStatement(sql, new String[] {"userid"});
				pstmt.setString(1, memberInfo.getUserid());
				pstmt.setString(2, memberInfo.getPassword());
				pstmt.setString(3, memberInfo.getName());
				pstmt.setDate(4, Date.valueOf(memberInfo.getBirthday()));
				pstmt.setBoolean(5, memberInfo.isGender());
				pstmt.setString(6, memberInfo.getEmail());
				pstmt.setString(7, memberInfo.getPhone());
				pstmt.setString(8, memberInfo.getPhoto());
				return pstmt;
			}
		}, keyHolder);
		

//		resultCnt = jdbcTemplate.update(sql, memberInfo.getUserid(), memberInfo.getPassword(), memberInfo.getName(),
//				memberInfo.getBirthday(), memberInfo.isGender(), memberInfo.getEmail(), memberInfo.getPhone(),
//				memberInfo.getPhoto());
		
		return resultCnt;
	}

	public int selectCount() {

		Integer resultCnt = 0;
		String sql = "select count(*) from member";

		resultCnt = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(resultCnt);
		return resultCnt;
	}

	public List<MemberInfo> selectList(int firstRow, int endRow) {

		String sql = "select userid, password, NAME, birthday, gender, email, phone, photo  from ( "
				+ " select rownum rnum, userid, password, NAME, birthday, gender, email, phone, photo from ( "
				+ " select * from member m order by m.userid desc " + " ) where rownum <= ? " + ") where rnum >= ?";

		List<MemberInfo> result = jdbcTemplate.query(sql, new MemberRowMapper(), endRow, firstRow);

		if (!result.isEmpty())
			return result;
		else
			return Collections.emptyList();
	}

}
