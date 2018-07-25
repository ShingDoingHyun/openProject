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
			conn = ConnectionProvider.getConnection();//Ŀ�ؼ� ������ �����´�.
			conn.setAutoCommit(false);//�ڵ����� DB�� �����Ű��(commit) �ʰ��Ѵ�. Ʈ������
			MessageDao messageDao = MessageDao.getInstance();
			messageDao.delete(conn, messageId);// �������� ����
			conn.commit();//Ŀ���Ͽ� DB�� �����Ѵ�.Ʈ������ �Ϸ�
		} catch (SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new ServiceException("���� ó�� �� ������ �߻��߽��ϴ�:" + ex.getMessage(), ex);
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
