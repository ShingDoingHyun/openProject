package com.bitcamp.op.member.service;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.dao.MemberDao;
import com.bitcamp.op.member.model.MemberInfo;

public class MemberJoinService {

	MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public int joinMember(MemberInfo memberInfo, HttpServletRequest request) throws Exception {

		int result = 0;
		Connection conn = null;
		// 저장용 파일이름 , 물리적저장, DB 저장용
		String imgName = "";

		// 1. 저장 경로 설정
		String uploadUri = "/uploadFile/memberPhoto";
		// 2. 시스템의 물리적인 경로
		String dir = request.getSession().getServletContext().getRealPath(uploadUri);
		
		System.out.println(dir);
		
		if(!memberInfo.getPhone().isEmpty()) {
			imgName = System.currentTimeMillis()+memberInfo.getPhotoFile().getOriginalFilename();
		
			memberInfo.getPhotoFile().transferTo(new File(dir, imgName));
			
			memberInfo.setPhoto(imgName);
		}
		

		try {

			conn = ConnectionProvider.getConnection();
			result = memberDao.insertMember(conn, memberInfo);
		} finally {
			JdbcUtil.close(conn);
		}

		return result;

	}

}
