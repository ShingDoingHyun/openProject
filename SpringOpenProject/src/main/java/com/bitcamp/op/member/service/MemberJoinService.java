package com.bitcamp.op.member.service;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.model.MemberInfo;

public class MemberJoinService {

	@Autowired
	JdbcTemplateMemberDao memberDao;

	public int joinMember(MemberInfo memberInfo, HttpServletRequest request) throws Exception {

		int result = 0;

		String imgName = "";

		// 1. 저장 경로 설정
		String uploadUri = "/uploadFile/memberPhoto";
		// 2. 시스템의 물리적인 경로
		String dir = request.getSession().getServletContext().getRealPath(uploadUri);

		if (!memberInfo.getPhone().isEmpty()) {
			imgName = System.currentTimeMillis() + memberInfo.getPhotoFile().getOriginalFilename();

			memberInfo.getPhotoFile().transferTo(new File(dir, imgName));

			memberInfo.setPhoto(imgName);
		}

		result = memberDao.insertMember(memberInfo);

		return result;

	}

}
