package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import guestBook.model.MessageListView;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberInfo;
import member.model.MemberInfoList;

public class GetMemberListService {
	private GetMemberListService() {
	}

	private static GetMemberListService instance = new GetMemberListService();

	public static GetMemberListService getInstance() {
		return instance;
	}
	
	// �� �������� ������ �޽����� ��
		private static final int MESSAGE_COUNT_PER_PAGE = 10;

		public MemberInfoList getMessageList(int pageNumber) throws ServiceException {
			Connection conn = null;
			
			int currentPageNumber = pageNumber > 0 ? pageNumber : 1; //�̵��ϴ� �������� �����ų� 0���� �̵��� �ڵ����� ù������1�� �������ش�.
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO memberDAO = MemberDAO.getInstance();
				// ��ü �޽��� ���ϱ�
				int messageTotalCount = memberDAO.selectCount(conn);//��ü �޼��� ���� ���ϱ�
				List<MemberInfo> memberList = null;
				int firstRow = 0;
				int endRow = 0;
				if (messageTotalCount > 0) {
					firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;	//(������-1) * �������� �޼��� ũ��+1 1~4~7
					endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;//(ù�޼��� ��ġ) + �������� �޼��� ũ�� 3~6~9
					memberList = memberDAO.selectList(conn, firstRow, endRow);
				} else {
					currentPageNumber = 0;
					memberList = Collections.emptyList();//null�� �ƴ� size�� 0���� ��ȯ
				}
				return new MemberInfoList(memberList, messageTotalCount, currentPageNumber, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
			} catch (SQLException e) {
				throw new ServiceException("�޽��� ��� ���ϱ� ����: " + e.getMessage(), e);
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		public MemberInfoList getMessageList() throws ServiceException {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO memberDAO = MemberDAO.getInstance();
				List<MemberInfo> memberList = null;
				memberList = memberDAO.selectList(conn);
				return new MemberInfoList(memberList);
			} catch (SQLException e) {
				throw new ServiceException("�޽��� ��� ���ϱ� ����: " + e.getMessage(), e);
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		
}
