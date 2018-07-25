package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberInfo;

public class DeleteMemberService {
	private DeleteMemberService() {
	}

	private static DeleteMemberService instance = new DeleteMemberService();

	public static DeleteMemberService getInstance() {
		return instance;
	}

	public int delete(String userid) throws ServiceException  {
		Connection conn=null;
		try {
			conn =  ConnectionProvider.getConnection();			
			MemberDAO memberdao = MemberDAO.getInstance();
			return memberdao.deleteMember(conn, userid);
		} catch (SQLException e) {
			throw new ServiceException("회원가입 등록 실패: " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}

	}

	
}
