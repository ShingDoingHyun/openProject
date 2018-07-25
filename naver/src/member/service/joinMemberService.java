package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberInfo;

public class joinMemberService {
	private joinMemberService() {
	}

	private static joinMemberService instance = new joinMemberService();

	public static joinMemberService getInstance() {
		return instance;
	}

	public int join(MemberInfo memberinfo) throws ServiceException  {
		Connection conn=null;
		try {
			conn =  ConnectionProvider.getConnection();			
			MemberDAO memberdao = MemberDAO.getInstance();
			return memberdao.insertMember(conn, memberinfo);
		} catch (SQLException e) {
			throw new ServiceException("회원가입 등록 실패: " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}

	}

	
}
