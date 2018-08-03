package com.bitcamp.op.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.model.MemberInfo;

public class MybatisMemberDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public MemberInfo selectById(String userId) {

		String str = "com.bitcamp.op.mapper.mybatis.MemberMapper.selectById";

		System.out.println("왜 에러 발생하지");
		Object obj = sqlSessionTemplate.selectOne(str, userId);
		System.out.println(obj);
		return (MemberInfo) obj;
	}

	public int insertMember(MemberInfo memberInfo) {

		String str = "com.bitcamp.op.mapper.mybatis.MemberMapper.insertMember";
		return sqlSessionTemplate.update(str, memberInfo);
	}
}
