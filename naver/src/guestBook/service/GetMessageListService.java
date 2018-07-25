package guestBook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import guestBook.dao.MessageDao;
import guestBook.model.Message;
import guestBook.model.MessageListView;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class GetMessageListService {
	private static GetMessageListService instance = new GetMessageListService();

	public static GetMessageListService getInstance() {
		return instance;
	}

	private GetMessageListService() {
	}

	// �� �������� ������ �޽����� ��
	private static final int MESSAGE_COUNT_PER_PAGE = 3;

	public MessageListView getMessageList(int pageNumber) throws ServiceException {
		Connection conn = null;
		
		int currentPageNumber = pageNumber > 0 ? pageNumber : 1; //�̵��ϴ� �������� �����ų� 0���� �̵��� �ڵ����� ù������1�� �������ش�.
		try {
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = MessageDao.getInstance();
			// ��ü �޽��� ���ϱ�
			int messageTotalCount = messageDao.selectCount(conn);//��ü �޼��� ���� ���ϱ�
			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			if (messageTotalCount > 0) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;	//(������-1) * �������� �޼��� ũ��+1 1~4~7
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;//(ù�޼��� ��ġ) + �������� �޼��� ũ�� 3~6~9
				messageList = messageDao.selectList(conn, firstRow, endRow);
			} else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();//null�� �ƴ� size�� 0���� ��ȯ
			}
			return new MessageListView(messageList, messageTotalCount, currentPageNumber, MESSAGE_COUNT_PER_PAGE,
					firstRow, endRow);
		} catch (SQLException e) {
			throw new ServiceException("�޽��� ��� ���ϱ� ����: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
