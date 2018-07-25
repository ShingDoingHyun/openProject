package guestBook.service;

import java.sql.Connection;
import java.sql.SQLException;

import guestBook.dao.MessageDao;
import guestBook.model.Message;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteMessageService {

	//�̱�������
	private WriteMessageService() {
	}

	private static WriteMessageService instance = new WriteMessageService();

	public static WriteMessageService getInstance() {
		return instance;
	}

	public int write(Message message) throws ServiceException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();//Ŀ�ؼ� ����
			MessageDao messageDao = MessageDao.getInstance();//�̱������� ������ �޼���dao�� �����´�
			int resultCnt  = messageDao.insert(conn, message);
			return resultCnt;
		} catch (SQLException e) {
			throw new ServiceException("�޽��� ��� ����: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
