package com.bitcamp.op.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bitcamp.op.member.model.MemberInfo;

public interface MemberDaoInterface {

	public MemberInfo selectById(String userid);
	public int insertMember(MemberInfo memberInfo);
	public List<MemberInfo> selectList(@Param(value="endRow")  int endRow, @Param("firstRow") int firstRow,  @Param("searchName") String searchName);
	public int selectCount();
}
