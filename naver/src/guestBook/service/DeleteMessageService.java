package guestBook.service;

import java.sql.Connection;
import java.sql.SQLException;

import guestBook.dao.MessageDao;
import guestBook.model.Message;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteMessageService {
	private static DeleteMessageService instance = new DeleteMessageService();

	public static DeleteMessageService getInstance() {
		return instance;
	}

	private DeleteMessageService() {
	}

	public void deleteMessage(int messageId)
			throws ServiceException, InvalidMessagePassowrdException, MessageNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();//커넥션 정보를 가져온다.
			conn.setAutoCommit(false);//자동으로 DB에 적용시키지(commit) 않게한다. 트랜젝션
			MessageDao messageDao = MessageDao.getInstance();
			messageDao.delete(conn, messageId);// 삭제쿼리 실행
			conn.commit();//커밋하여 DB에 적용한다.트랜젝션 완료
		} catch (SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new ServiceException("삭제 처리 중 에러가 발생했습니다:" + ex.getMessage(), ex);
		}finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(false);
				} catch (SQLException e) {
				}
				JdbcUtil.close(conn);
			}
		}
	}
}
