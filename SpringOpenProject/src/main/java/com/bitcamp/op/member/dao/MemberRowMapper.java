package com.bitcamp.op.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bitcamp.op.member.model.MemberInfo;

public class MemberRowMapper implements RowMapper<MemberInfo>{

	@Override
	public MemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
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

}
