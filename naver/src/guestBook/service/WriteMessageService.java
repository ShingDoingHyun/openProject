package guestBook.service;

import java.sql.Connection;
import java.sql.SQLException;

import guestBook.dao.MessageDao;
import guestBook.model.Message;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteMessageService {

	//싱글톤패턴
	private WriteMessageService() {
	}

	private static WriteMessageService instance = new WriteMessageService();

	public static WriteMessageService getInstance() {
		return instance;
	}

	public int write(Message message) throws ServiceException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();//커넥션 연결
			MessageDao messageDao = MessageDao.getInstance();//싱글콘으로 생성된 메세지dao를 가져온다
			int resultCnt  = messageDao.insert(conn, message);
			return resultCnt;
		} catch (SQLException e) {
			throw new ServiceException("메시지 등록 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
